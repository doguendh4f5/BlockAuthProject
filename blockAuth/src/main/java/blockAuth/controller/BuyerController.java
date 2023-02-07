package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blockAuth.command.BuyerCommand;
import blockAuth.service.BuyerNumberService;
import blockAuth.service.BuyerRegistService;

@Controller
@RequestMapping("buyer")
public class BuyerController {
	@RequestMapping("buyerList")
	public String buyerList() {
		return "thymeleaf/buyer/buyerList";
	}
	@Autowired
	BuyerNumberService buyerNumberService;
	@RequestMapping(value = "buyerRegist", method = RequestMethod.GET)
	public String buyerWrite(BuyerCommand buyerCommand) {
		buyerNumberService.execute(buyerCommand);
		return "thymeleaf/buyer/buyerForm";
	}
	@Autowired
	BuyerRegistService buyerRegistService;
	@RequestMapping(value = "buyerRegist", method = RequestMethod.POST)
	public String buyerRegist(BuyerCommand buyerCommand) {
		return "thymeleaf/buyer/buyerForm";
	}
}
