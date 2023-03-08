package blockAuth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.service.mypage.BuyerMyInfoEditService;
import blockAuth.service.purchase.OrderSheetService;
import blockAuth.service.purchase.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderSheetService orderSheetService;
	@Autowired
	BuyerMyInfoEditService buyerMyInfoEditService;
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model, HttpServletRequest request) {
		long price = orderSheetService.execute(prodCk, session, model);
		buyerMyInfoEditService.execute(session, model);
		paymentService.execute(price, model);
		
		
		return "thymeleaf/purchase/orderSheet";
		// return "pay/orderSheet2";
	}
	/*
	  @RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model) {
		orderSheetService.execute(prodCk, session, model);
		return "thymeleaf/purchase/orderSheet";
	}
	 */
	
}
