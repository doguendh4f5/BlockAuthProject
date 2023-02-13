package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blockAuth.service.IdCheckService;

@RestController
public class CheckRestController {
	@Autowired
	IdCheckService idCheckService;
	@RequestMapping("buyerIdCheck")
	public String buyerIdCheck(
			@RequestParam(value = "buyerId")String buyerId) {
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
}
