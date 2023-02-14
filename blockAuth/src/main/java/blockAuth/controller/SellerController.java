package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.command.SellCommand;
import blockAuth.service.FileDownload;
import blockAuth.service.contract.ContractNumberService;
import blockAuth.service.seller.SellerDeleteService;
import blockAuth.service.seller.SellerDetailService;
import blockAuth.service.seller.SellerInfoservice;
import blockAuth.service.seller.SellerListService;
import blockAuth.service.seller.SellerModifyService;
import blockAuth.service.seller.SellerNumService;
import blockAuth.service.seller.SellerRegiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("seller")
public class SellerController {

	@Autowired
	SellerNumService sellerNumService;
	@Autowired
	SellerRegiService sellerRegiService;
	@Autowired
	SellerListService sellerListService;
	@Autowired
	SellerInfoservice sellerInfoservice;
	@Autowired
	SellerModifyService sellerModifyService;
	@Autowired
	SellerDetailService sellerDetailService;
	@Autowired
	SellerDeleteService sellerDeleteService;
	@Autowired
	ContractNumberService contractNumberService;
	@Autowired
	FileDownload fileDownload;
	
	@RequestMapping("sellerList")
	public String sellerList(Model model) {
		sellerListService.execute(model);
		return "thymeleaf/seller/sellerList";
	}
	
	@RequestMapping(value = "sellerRegist", method = RequestMethod.GET)
	public String sellerRegist(SellCommand sellCommand) {
		sellerNumService.execute(sellCommand);
		return "thymeleaf/seller/sellerForm";
	}
	
	@RequestMapping(value = "sellerRegist", method = RequestMethod.POST)
	public String sellerRegist(@Validated SellCommand sellCommand
				, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/seller/sellerForm";
		}
		
		if(!sellCommand.isSellerPwEqualsSellerPwCon()) {
			result.rejectValue("sellerPw", "sellCommand.sellerPw", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/seller/sellerForm";
		}
		Integer i = sellerRegiService.execute(sellCommand);
		sellerRegiService.createPdf(sellCommand, model);		
		
		if(i != null) {
			return "thymeleaf/seller/sellerContractCheck";
		}else {
			return "thymeleaf/seller/sellerList";
		}
	}
	
	@RequestMapping("sellerInfo")
	public String sellerInfo(@RequestParam(value="num") String sellerNum
				, Model model) {
		sellerInfoservice.execute(sellerNum, model);
		return "thymeleaf/seller/sellerInfo";
	}
	
	@RequestMapping(value="sellerModify", method = RequestMethod.GET)
	public String sellerModify(@RequestParam(value="sellerNum") String sellerNum
			, Model model) {
		sellerDetailService.execute(sellerNum, model);
		return "thymeleaf/seller/sellerUpdate";
	}
	
	@RequestMapping(value="sellerModify", method = RequestMethod.POST)
	public String sellerModify(@Validated SellCommand sellCommand
			, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/seller/sellerUpdate";
		}
		sellerModifyService.execute(sellCommand);
		return "redirect:sellerInfo?num="+sellCommand.getSellerNum();
	}
	
	@RequestMapping("sellerDelete")
	public String sellerDelete(@RequestParam(value = "sellerNum") String sellerNum) {
		sellerDeleteService.execute(sellerNum);
		return "redirect:sellerList";
	}
	
	/*
	@RequestMapping(value = "sellerContractInfo")
	public String sellerContractInfo(@RequestParam(value = "sellerNum") String sellerNum, Model model) {
		sellerContractService.execute(sellerNum, model);
		return "thymeleaf/seller/sellerContractInfo";
	}
	*/
	
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, String fileName) {
		fileDownload.fileDownLoad("src/main/resources/static/contTempFiles/", fileName, fileName, request, response);
	}
}
