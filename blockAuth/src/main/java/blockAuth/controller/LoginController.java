package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blockAuth.command.LoginCommand;
import blockAuth.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(LoginCommand loginCommand, HttpSession session) {
		
		return "thymeleaf/login";
	}
	@RequestMapping(value = "login/loginOk", method = RequestMethod.GET)
	public String home() {
		
		return "redirect:/login";
	}
	@Autowired
	LoginService loginService;
	@RequestMapping(value = "login/loginOk", method = RequestMethod.POST)
	public String loginOk(@Validated LoginCommand loginCommand,
			BindingResult result, HttpSession session, HttpServletResponse response){
		if(result.hasErrors()) {
			return "thymeleaf/login";
		}
		String i = loginService.execute(loginCommand, result, session, response);
		return i;
	}
	@RequestMapping(value = "login/logout")
	public String logout(LoginCommand loginCommand, HttpSession session, HttpServletResponse response) {
		Cookie cookie = new Cookie("autoLogin", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		session.invalidate();
		return "redirect:/";
	}
}
