package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import blockAuth.command.LoginCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	LoginMapper loginMapper;

	public String execute(LoginCommand loginCommand, BindingResult result, HttpSession session,
			HttpServletResponse response) {
		AuthInfo authInfo = loginMapper.loginSelect(loginCommand.getUserId());
		if (authInfo != null) {
			if (!passwordEncoder.matches(loginCommand.getUserPw(), authInfo.getUserPw())) {
				result.rejectValue("userPw", "loginCommand.getUserPw()", "비밀번호가 틀렸습니다.");
				return "thymeleaf/login";
			} else {
				session.setAttribute("authInfo", authInfo);
				if(loginCommand.getIdSave() != null && loginCommand.getIdSave()) {
					Cookie cookie = new Cookie("idSave", authInfo.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idSave", "");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				
				return "redirect:/";
			}
		} else {
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
			return "thymeleaf/login";
		}
		

	}

}
