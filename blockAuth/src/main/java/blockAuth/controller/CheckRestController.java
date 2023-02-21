package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blockAuth.domain.AuthInfo;
import blockAuth.service.EmailCheckService;
import blockAuth.service.IdCheckService;

@RestController
public class CheckRestController {
	@Autowired
	IdCheckService idCheckService;
	@RequestMapping("buyerIdCheck")
	public String buyerIdCheck(
			@RequestParam(value = "buyerId")String buyerId) {
		System.out.println("buyerId" + buyerId);
		String checkId = idCheckService.execute(buyerId);
		String message = null;
		if(checkId == null) {
			message = "사용가능한 아이디입니다.";
		}else {
			message = "사용중인 아이디입니다.";
		}
		return message;
	}
	@RequestMapping("sellerIdCheck")
	public String sellerIdCheck(
			@RequestParam(value = "sellerId")String sellerId) {
		String checkId = idCheckService.execute(sellerId);
		String message = null;
		if(checkId == null) {
			message = "사용가능한 아이디입니다.";
		}else {
			message = "사용중인 아이디입니다.";
		}
		return message;
	}
	@Autowired
	EmailCheckService emailCheckService;
	@RequestMapping("buyerEmailCheck")
	public String buyerEmailCheck(
			@RequestParam(value = "buyerEmail")String buyerEmail,
			@RequestParam(value = "buyerId")String buyerId) {
		AuthInfo authInfo = emailCheckService.execute(buyerEmail);
		if(authInfo == null) {
			return "사용가능한 이메일입니다.";
		}else {
			if(authInfo.getUserId().equals(buyerId)) {
				return "사용가능한 이메일입니다.";
			}else {
				return "사용중인 이메일입니다.";
			}
		}
		
	}
}
