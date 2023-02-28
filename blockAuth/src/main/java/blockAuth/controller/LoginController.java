package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.command.LoginCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.mapper.LoginMapper;
import blockAuth.service.CookieService;
import blockAuth.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	CookieService cookieService;
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(LoginCommand loginCommand, HttpSession session,
			HttpServletRequest request, Model model) {
		cookieService.execute(request, model);
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
	
	// 인증서를 이용한 로그인
	@Autowired
	LoginMapper loginMapper;
	@RequestMapping(value = "/login/privateLogin", method = RequestMethod.POST)
	public @ResponseBody String privateLogin(
			@RequestParam(value = "address") String address, HttpSession session) {
		AuthInfo authInfo = loginMapper.addressLogin(address);
		if(authInfo != null) {
			session.setAttribute("authInfo", authInfo); // 세션에 정보 저장
			return "1";
		}else {
			return "0";
		}
	}
}
