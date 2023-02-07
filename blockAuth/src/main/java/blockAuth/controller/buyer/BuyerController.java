package blockAuth.controller.buyer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("buyer")
public class BuyerController {
	@RequestMapping("buyerList")
	public String buyerList() {
		return "";
	}
}
