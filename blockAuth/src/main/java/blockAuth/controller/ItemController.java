package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.service.goods.GoodsDetailService;

@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	GoodsDetailService goodsDetailService;
	@RequestMapping("itemDetailContent")
	public String itemDetailContent(
			@RequestParam("goodsNum")String goodsNum,
			Model model) {
		goodsDetailService.execute(model, goodsNum);
		return "thymeleaf/item/itemDetailContent";
	}
	@RequestMapping("reviewContent")
	public String itemReview() {
		
		return "thymeleaf/item/reviewContent";
	}
}
