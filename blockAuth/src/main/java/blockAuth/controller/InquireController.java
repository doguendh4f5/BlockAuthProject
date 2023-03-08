package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.command.InquireCommand;
import blockAuth.service.inquire.BuyerInqDetailService;
import blockAuth.service.inquire.BuyerInqNumberService;
import blockAuth.service.inquire.BuyerInqRegiService;
import blockAuth.service.inquire.InqDetailService;
import blockAuth.service.inquire.InquireListChooseService;
import blockAuth.service.inquire.SellerAdminReplyInsertService;
import blockAuth.service.inquire.SellerInqDelService;
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
	InqDetailService inqDetailService;
	@Autowired
	SellerInqModifyService sellerInqModifyService;
	@Autowired
	SellerAdminReplyInsertService sellerAdminReplyInsertService;
	@Autowired
	SellerInqDelService sellerInqDelService;
	@Autowired
	BuyerInqNumberService buyerInqNumberService;
	@Autowired
	BuyerInqRegiService buyerInqRegiService;
	@Autowired
	BuyerInqDetailService buyerInqDetailService;
	@Autowired
	InquireListChooseService inquireListChooseService;
	
	@RequestMapping("inquireList")
	public String inquireList(Model model, HttpSession session) {
		String url = sellerInqListService.execute(model, session);
		return url;
	}
	
	@RequestMapping("inquireListAdmin/seller")
	public String inquireListSeller(Model model, HttpSession session) {
		String str = "seller";
		String url = inquireListChooseService.execute(model, session, str);
		return url;
	}
	@RequestMapping("inquireListAdmin/buyer")
	public String inquireListBuyer(Model model, HttpSession session) {
		String str = "buyer";
		String url = inquireListChooseService.execute(model, session, str);
		return url;
	}
	
	@RequestMapping(value = "sellerInqRegist", method = RequestMethod.GET)
	public String sellerInqRegist(InquireCommand inquireCommand) {
		sellerInqNumberService.execute(inquireCommand);
		return "thymeleaf/inquire/InquireForm";
	}
	
	@RequestMapping(value = "sellerInqRegist", method = RequestMethod.POST)
	public String sellerInqRegist(InquireCommand inquireCommand
			, HttpSession session) {
		sellerInqRegiService.execute(inquireCommand, session);
		return "redirect:inquireList";
	}
	
	@RequestMapping("sellerInqDetail")
	public String sellerInqDetail(
			@RequestParam(value = "adminInqNum") String adminInqNum
			, Model model) {
		inqDetailService.execute(model, adminInqNum);
		return "thymeleaf/inquire/inquireDetail";
	}
	
	@RequestMapping(value = "inquireUpdate", method = RequestMethod.GET)
	public String sellerInquireUpdate(
			@RequestParam(value = "adminInqNum") String adminInqNum
			, Model model) {
		inqDetailService.execute(model, adminInqNum);
		return "thymeleaf/inquire/inquireUpdate";
	}
	
	@RequestMapping(value = "sellerInqModify", method = RequestMethod.POST)
	public String sellerInqModify(InquireCommand inquireCommand
				, Model model, HttpSession session) {
		sellerInqModifyService.execute(inquireCommand, model, session);
		return "redirect:sellerInqDetail?adminInqNum="+inquireCommand.getAdminInqNum();
	}

	@RequestMapping(value = "sellerAdminReplyInsert", method = RequestMethod.POST)
	public @ResponseBody String sellerAdminReplyInsert(@RequestParam(value = "adminInqNum") String adminInqNum
				,@RequestParam(value = "adminReplyContent") String adminReplyContent) {
		String i = sellerAdminReplyInsertService.execute(adminReplyContent, adminInqNum);
		return i;
	}
	
	@RequestMapping(value = "sellerInqDel")
	public String sellerInqDel(@RequestParam(value = "adminInqNum") String adminInqNum) {
		sellerInqDelService.execute(adminInqNum);
		return "redirect:inquireList";
	}
	
	
	
	// buyer Inq
	@RequestMapping(value = "buyerInqRegist", method = RequestMethod.GET)
	public String buyerInqRegist(InquireCommand inquireCommand) {
		buyerInqNumberService.execute(inquireCommand);
		return "thymeleaf/inquire/InquireForm";
	}
	
	@RequestMapping(value = "buyerInqRegist", method = RequestMethod.POST)
	public String buyerInqRegist(InquireCommand inquireCommand
			, HttpSession session) {
		buyerInqRegiService.execute(inquireCommand, session);
		return "redirect:inquireList";
	}
	
	@RequestMapping("buyerInqDetail")
	public String buyerInqDetail(
			@RequestParam(value = "adminInqNum") String adminInqNum
			, Model model) {
		buyerInqDetailService.execute(model, adminInqNum);
		return "thymeleaf/inquire/inquireDetail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
