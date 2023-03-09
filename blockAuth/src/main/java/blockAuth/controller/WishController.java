package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.service.goods.GoodsDetailService;
import blockAuth.service.wish.GoodsWishCheckService;
import blockAuth.service.wish.GoodsWishService;
import blockAuth.service.wish.WishListService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WishController {
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsWishCheckService goodsWishCheckService;
	public String wishDetail(
			@RequestParam(value = "goodsNum")String goodsNum,
			Model model, HttpSession session) {
		goodsDetailService.execute(model, goodsNum);
		goodsWishCheckService.execute(goodsNum, session, model);
		return "thymeleaf/goods/goodsDetail";
	}
	@Autowired
	GoodsWishService goodsWishService;
	@RequestMapping(value = "goodsWishAdd", method = RequestMethod.POST)
	public @ResponseBody String goodsWishAdd(
			@RequestParam(value = "goodsNum")String goodsNum,
			HttpSession session) {
		
		return goodsWishService.execute(goodsNum, session);
	}
	@Autowired
	WishListService wishListService;
	@RequestMapping("wish/wishList")
	public String wishList(Model model, HttpSession session) {
		wishListService.execute(model, session);
		return "thymeleaf/wish/wishList";
	}
}
