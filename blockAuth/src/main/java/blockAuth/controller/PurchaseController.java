package blockAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PurchaseController {
	@RequestMapping(value = "purchaseList")
	public String purchaseList() {
		
		return "thymeleaf/purchase/purchaseList";
	}
}
