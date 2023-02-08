package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.command.BuyerCommand;
import blockAuth.service.buyer.BuyerDeleteService;
import blockAuth.service.buyer.BuyerDetailService;
import blockAuth.service.buyer.BuyerListService;
import blockAuth.service.buyer.BuyerModifyService;
import blockAuth.service.buyer.BuyerNumberService;
import blockAuth.service.buyer.BuyerRegistService;

@Controller
@RequestMapping("buyer")
public class BuyerController {
	@Autowired
	BuyerListService buyerListService;
	@RequestMapping("buyerList")
	public String buyerList(Model model) {
		buyerListService.execute(model);
		return "thymeleaf/buyer/buyerList";
	}
	@Autowired
	BuyerNumberService buyerNumberService;
	@RequestMapping(value = "buyerRegist", method = RequestMethod.GET)
	public String buyerRegist(BuyerCommand buyerCommand) {
		buyerNumberService.execute(buyerCommand);
		return "thymeleaf/buyer/buyerForm";
	}
	@Autowired
	BuyerRegistService buyerRegistService;
	@RequestMapping(value = "buyerRegist", method = RequestMethod.POST)
	public String buyerWrite(@Validated BuyerCommand buyerCommand,
			BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/buyer/buyerForm";
		}
		if(!buyerCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("buyerPw", "buyerCommand.buyerPw", 
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/buyer/buyerForm";
		}
		Integer i = buyerRegistService.execute(buyerCommand);
		if(i != null) {
			return "redirect:buyerList";
		}else {
			return "thymeleaf/buyer/buyerForm";
		}
	}
	@Autowired
	BuyerDetailService buyerDetailService;
	@RequestMapping(value = "buyerDetail/{buyerNum}")
	public String buyerDetail(
			@PathVariable(value = "buyerNum")String buyerNum,
			Model model) {
		buyerDetailService.execute(buyerNum, model);
		return "thymeleaf/buyer/buyerDetail";
	}
	@RequestMapping(value = "buyerModify", method = RequestMethod.GET)
	public String buyerUpdate(
			@RequestParam(value = "buyerNum")String buyerNum,
			Model model) {
		buyerDetailService.execute(buyerNum, model);
		return "thymeleaf/buyer/buyerUpdate";
	}
	@Autowired
	BuyerModifyService buyerModifyService;
	@RequestMapping(value = "buyerModify", method = RequestMethod.POST)
	public String buyerModify(@Validated BuyerCommand buyerCommand,
			BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/buyer/buyerUpdate";
		}
		buyerModifyService.execute(buyerCommand);
		return "redirect:buyerDetail/"+buyerCommand.getBuyerNum();
	}
	@Autowired
	BuyerDeleteService buyerDeleteService;
	@RequestMapping("buyerDelete")
	public String buyerDelete(
			@RequestParam(value = "buyerNum")String buyerNum) {
		buyerDeleteService.execute(buyerNum);
		return "redirect:buyerList";
	}
}
