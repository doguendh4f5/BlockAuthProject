package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blockAuth.service.mypage.SellerMyInfoEditService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "sellerMyPage")
public class SellerMyPageController {
	@Autowired
	SellerMyInfoEditService sellerMyInfoEditService;
	
	@RequestMapping(value = "")
	public String sellerMyPage(){
		return "thymeleaf/sellerMyPage/sellerMyPage";
	}
	
	@RequestMapping(value = "withdrawal")
	public String withdrawal() {
		return "thymeleaf/sellerMyPage/sellerWithdrawal";
	}
	
	@RequestMapping("sellerContract")
	public String sellerContract(HttpSession session ,Model model) {
		sellerMyInfoEditService.execute(session, model);
		return "thymeleaf/sellerMyPage/sellerContractInfo";
	}
}
