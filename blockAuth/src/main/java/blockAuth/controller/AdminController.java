package blockAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
	public String insert() {
		return "thymeleaf/admin/buyer";
	}
}
