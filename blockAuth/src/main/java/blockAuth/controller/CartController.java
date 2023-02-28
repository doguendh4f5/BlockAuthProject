package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.service.cart.CartAddService;
import blockAuth.service.cart.CartListService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	CartListService cartListService;
	@RequestMapping("cartList")
	public String cartList(Model model, HttpSession session) {
		
		return cartListService.execute(model, session);
	}
	@Autowired
	CartAddService cartAddService;
	@RequestMapping("cartAdd")
	public @ResponseBody String cartAdd(
			@RequestParam(value = "goodsNum")String goodsNum,
			@RequestParam(value = "goodsStock") Long goodsStock,
			HttpSession session) {
		
		return cartAddService.execute(goodsNum, goodsStock, session);
	}
}
