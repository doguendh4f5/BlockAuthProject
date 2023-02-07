package blockAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
	@RequestMapping("adminList")
	public String insert() {
		return "thymeleaf/admin/adminList";
	}
}
