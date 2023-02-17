package blockAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InquireController {
	@RequestMapping("inquireList")
	public String inquireList() {
		return "thymeleaf/inquire/inquireList";
	}
}
