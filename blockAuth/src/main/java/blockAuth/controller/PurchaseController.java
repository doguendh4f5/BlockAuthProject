package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.service.mypage.BuyerMyInfoEditService;
import blockAuth.service.purchase.OrderSheetService;
import blockAuth.service.purchase.OrderSheetService2;
import blockAuth.service.purchase.PaymentSaveService;
import blockAuth.service.purchase.PaymentService;
import blockAuth.service.purchase.PurchaseService;
import blockAuth.service.purchase.PurchaseService2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderSheetService orderSheetService;
	@Autowired
	OrderSheetService2 orderSheetService2;
	@Autowired
	BuyerMyInfoEditService buyerMyInfoEditService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	PurchaseService2 purchaseService2;
	@Autowired
	PaymentSaveService paymentSaveService;
	
	@RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model, HttpServletRequest request) {
		orderSheetService.execute(prodCk, session, model);
		buyerMyInfoEditService.execute(session, model);

		return "thymeleaf/purchase/orderSheet";
		// return "pay/orderSheet2";
	}
	
	@RequestMapping("orderSheet2")
	public String orderSheet2(HttpSession session, Model model, HttpServletRequest request,
							@RequestParam(value = "goodsNum") String goodsNum,
							@RequestParam(value = "goodsStock") String goodsStock) {
		orderSheetService2.execute(session, model, goodsNum, goodsStock);
		buyerMyInfoEditService.execute(session, model);

		return "thymeleaf/purchase/orderSheet2";
		
	}
	
	@RequestMapping("purchaseOk")
	public String purchaseOk(Model model, HttpSession session,
							@RequestParam(value = "goodsNums") String goodsNums,
							@RequestParam(value = "totalPrice") String totalPrice,
							@RequestParam(value = "receiverName") String receiverName,
							@RequestParam(value = "deliveryAddr") String deliveryAddr,
							@RequestParam(value = "deliveryAddr2") String deliveryAddr2,
							@RequestParam(value = "deliveryPostcode") String deliveryPostcode,
							@RequestParam(value = "receiverPhone") String receiverPhone,
							@RequestParam(value = "deliveryReq") String deliveryReq) {
		String purchaseNum = purchaseService.execute(session, totalPrice, receiverName, deliveryAddr, deliveryAddr2, deliveryPostcode, receiverPhone, deliveryReq, goodsNums);
		
		return "redirect:paymentOk?purchaseNum=" + purchaseNum + "&totalPrice=" + totalPrice;
	}
	
	@RequestMapping("purchaseOk2")
	public String purchaseOk2(Model model, HttpSession session,
							@RequestParam(value = "goodsNums") String goodsNums,
							@RequestParam(value = "totalPrice") String totalPrice,
							@RequestParam(value = "receiverName") String receiverName,
							@RequestParam(value = "deliveryAddr") String deliveryAddr,
							@RequestParam(value = "deliveryAddr2") String deliveryAddr2,
							@RequestParam(value = "deliveryPostcode") String deliveryPostcode,
							@RequestParam(value = "receiverPhone") String receiverPhone,
							@RequestParam(value = "deliveryReq") String deliveryReq) {
		String purchaseNum = purchaseService2.execute(session, totalPrice, receiverName, deliveryAddr, deliveryAddr2, deliveryPostcode, receiverPhone, deliveryReq, goodsNums);
		
		return "redirect:paymentOk?purchaseNum=" + purchaseNum + "&totalPrice=" + totalPrice;
	}
	
	@RequestMapping("paymentOk")
	public String paymentOk(@RequestParam(value = "purchaseNum") String purchaseNum, 
							@RequestParam(value = "totalPrice") String totalPrice,
							Model model, HttpSession session) {
		paymentService.execute(totalPrice, purchaseNum, model, session);
		return "thymeleaf/purchase/paymentPage";
	}
	
	@RequestMapping("paymentSave")
	public @ResponseBody String paymentSave(@RequestParam(value = "purchaseNum") String purchaseNum,
			@RequestParam(value = "paymentMethod") String paymentMethod,
			@RequestParam(value = "paymentDate") String paymentDate,
			@RequestParam(value = "okDate") String okDate,
			@RequestParam(value = "cardCompany") String cardCompany,
			@RequestParam(value = "okNum") String okNum,
			@RequestParam(value = "creditNum") String creditNum) {
		int i = paymentSaveService.execute(purchaseNum, paymentMethod, paymentDate, okDate, cardCompany, okNum, creditNum);
		return Integer.toString(i);
	}
}
