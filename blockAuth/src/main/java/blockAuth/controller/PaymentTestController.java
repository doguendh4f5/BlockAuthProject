package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blockAuth.service.mypage.MembershipRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("paymentTest")
public class PaymentTestController {
	@Autowired
	MembershipRegistService membershipRegistService;

	@RequestMapping(value = "payment")
	public String payment(Model model) {
		return "pay/paymentReq";
	}
	
	@RequestMapping(value = "membershipPayment")
	public String membershipPayment(Model model) {
		return "pay/membershipPaymentReq";
	}
	
	@RequestMapping(value = "membershipReturn")
	public String membershipReturn(HttpSession session) {
		membershipRegistService.execute(session);
		return "redirect:../";
	}
	
	@RequestMapping(value = "paymentReturn")
	public String paymentReturn() {
		return "pay/paymentReturn";
	}
	
	@RequestMapping(value = "paymentClose")
	public String paymentClose() {
		return "pay/close";
	}
	
	@RequestMapping(value = "signTest")
	public String signTest() {
		return "thymeleaf/sign/signTest";
	}
}
