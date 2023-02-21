package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blockAuth.command.FindIdCommand;
import blockAuth.command.FindPwCommand;
import blockAuth.service.FindIdService;
import blockAuth.service.FindPwService;

@Controller
@RequestMapping("help")
public class HelpController {
	@Autowired
	FindIdService findIdService;
	@Autowired
	FindPwService findPwService;
	@RequestMapping(value = "findId", method = RequestMethod.GET)
	public String findId(
			@ModelAttribute("findIdCommand")FindIdCommand findIdCommand) {
		
		return "thymeleaf/help/findId";
	}
	@RequestMapping(value = "findId", method = RequestMethod.POST)
	public String findId(@Validated FindIdCommand findIdCommand,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findId";
		}
		String i = findIdService.execute(findIdCommand, result, model);
		return i;
	}
	@RequestMapping(value = "findPw", method = RequestMethod.GET)
	public String findPw(FindPwCommand findPwCommand) {
		
		return "thymeleaf/help/findPw";
	}
	@RequestMapping(value = "findPw", method = RequestMethod.POST)
	public String findPw(@Validated FindPwCommand findPwCommand,
			BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findPw";
		}
		String s = findPwService.execute(findPwCommand, result);
		return s;
	}
}
