package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.command.SellerInquireCommand;
import blockAuth.service.inquire.SellerInqDetailService;
import blockAuth.service.inquire.SellerInqListService;
import blockAuth.service.inquire.SellerInqModifyService;
import blockAuth.service.inquire.SellerInqNumberService;
import blockAuth.service.inquire.SellerInqRegiService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	SellerInqNumberService sellerInqNumberService;
	@Autowired
	SellerInqRegiService sellerInqRegiService;
	@Autowired
	SellerInqListService sellerInqListService;
	@Autowired
	SellerInqDetailService sellerInqDetailService;
	@Autowired
	SellerInqModifyService sellerInqModifyService;
	
	@RequestMapping("inquireList")
	public String inquireList(Model model) {
		sellerInqListService.execute(model);
		return "thymeleaf/inquire/inquireList";
	}
	
	@RequestMapping(value = "sellerInqRegist", method = RequestMethod.GET)
	public String sellerInqRegist(SellerInquireCommand sellerInquireCommand) {
		sellerInqNumberService.execute(sellerInquireCommand);
		return "thymeleaf/inquire/InquireForm";
	}
	
	@RequestMapping(value = "sellerInqRegist", method = RequestMethod.POST)
	public String sellerInqRegist(SellerInquireCommand sellerInquireCommand
			, HttpSession session) {
		sellerInqRegiService.execute(sellerInquireCommand, session);
		return "redirect:inquireList";
	}
	
	@RequestMapping("sellerInqDetail")
	public String sellerInqDetail(
			@RequestParam(value = "adminInqNum") String adminInqNum
			, Model model) {
		model.addAttribute("newLineChar", '\n');
		sellerInqDetailService.execute(model, adminInqNum);
		return "thymeleaf/inquire/inquireDetail";
	}
	
	@RequestMapping(value = "sellerInquireUpdate", method = RequestMethod.GET)
	public String sellerInquireUpdate(
			@RequestParam(value = "adminInqNum") String adminInqNum
			, Model model) {
		sellerInqDetailService.execute(model, adminInqNum);
		return "thymeleaf/inquire/inquireUpdate";
	}
	
	@RequestMapping(value = "sellerInqModify", method = RequestMethod.POST)
	public String sellerInqModify(SellerInquireCommand sellerInquireCommand
				, Model model, HttpSession session) {
		sellerInqModifyService.execute(sellerInquireCommand, model, session);
		return "redirect:sellerInqDetail?adminInqNum="+sellerInquireCommand.getAdminInqNum();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
