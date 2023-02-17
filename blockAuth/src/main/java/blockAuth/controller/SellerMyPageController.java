package blockAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "sellerMyPage")
public class SellerMyPageController {
	@RequestMapping(value = "")
	public String sellerMyPage(){
		return "thymeleaf/sellerMyPage/sellerMyPage";
	}
	
	@RequestMapping(value = "withdrawal")
	public String withdrawal() {
		return "thymeleaf/sellerMyPage/sellerWithdrawal";
	}
}
