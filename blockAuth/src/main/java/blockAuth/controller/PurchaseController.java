package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.service.purchase.OrderSheetService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderSheetService orderSheetService;
	@RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model) {
		orderSheetService.execute(prodCk, session, model);
		return "thymeleaf/purchase/orderSheet";
	}
}
