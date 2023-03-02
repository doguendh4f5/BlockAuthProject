package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.command.SellerInquireCommand;
import blockAuth.service.inquire.SellerAdminReplyInsertService;
import blockAuth.service.inquire.SellerInqDelService;
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
	@Autowired
	SellerAdminReplyInsertService sellerAdminReplyInsertService;
	@Autowired
	SellerInqDelService sellerInqDelService;
	
	@RequestMapping("inquireList")
	public String inquireList(Model model, HttpSession session) {
		String url = sellerInqListService.execute(model, session);
		return url;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
