package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blockAuth.command.BuyerCommand;
import blockAuth.command.SellCommand;
import blockAuth.service.buyer.BuyerJoinOkService;
import blockAuth.service.buyer.BuyerNumberService;
import blockAuth.service.seller.SellerJoinOkService;
import blockAuth.service.seller.SellerNumService;
import blockAuth.service.seller.SellerRegiService;

@Controller
public class JoinController {
	@RequestMapping("joinList")
	public String joinList() {
		return "thymeleaf/join/agree";
	}
	@RequestMapping("agree")
	public String agree() {
		return "thymeleaf/join/joinList";
	}
	@Autowired
	BuyerNumberService buyerNumberService; 
	@RequestMapping("buyerJoin")
	public String buyerJoin(BuyerCommand buyerCommand) {
		buyerNumberService.execute(buyerCommand);
		return "thymeleaf/join/buyerJoin";
	}
	@Autowired
	BuyerJoinOkService buyerJoinOkService;
	@RequestMapping(value = "buyerJoinOk", method = RequestMethod.POST)
	public String buyerJoinOk(@Validated BuyerCommand buyerCommand,
			BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/join/buyerJoin";
		}
		if(!buyerCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("buyerPw", "buyerCommand.buyerPw", 
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/join/buyerJoin";
		}
		Integer i = buyerJoinOkService.execute(buyerCommand);
		if(i != null) {
			return "redirect:/login";
		}else {
			return "thymeleaf/join/buyerJoin";
		}
	}
	@Autowired
	SellerNumService sellerNumService;
	@RequestMapping("sellerJoin")
	public String sellerJoin(SellCommand sellCommand) {
		sellerNumService.execute(sellCommand);
		return "thymeleaf/join/sellerJoin";
	}
	@Autowired
	SellerRegiService sellerRegiService;
	@Autowired
	SellerJoinOkService sellerJoinOkService;
	@RequestMapping(value = "sellerJoinOk")
	public String sellerJoinOk(@Validated SellCommand sellCommand,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/join/sellerJoin";
		}
		if(!sellCommand.isSellerPwEqualsSellerPwCon()) {
			result.rejectValue("sellerPw", "sellCommand.sellerPw", 
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/join/sellerJoin";
		}
		Integer i = sellerJoinOkService.execute(sellCommand);
		sellerRegiService.createPdf(sellCommand, model);
		
		if(i != null) {
			return "thymeleaf/seller/sellerContractCheck";
		}else {
			return "thymeleaf/join/sellerJoin";
		}
		
	}
}
