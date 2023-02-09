package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import blockAuth.command.LoginCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.mapper.LoginMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	LoginMapper loginMapper;
	public String execute(LoginCommand loginCommand, BindingResult result,
			HttpSession session, HttpServletResponse response) {
		AuthInfo authInfo = loginMapper.loginSelect(loginCommand.getUserId());
		if(authInfo != null) {
			if(authInfo.getUserId() != loginCommand.getUserId()) {
				result.rejectValue("userId", "loginCommand.getUserId()",
						"아이디를 확인해주세요.");
			}
		
		if(!passwordEncoder.matches(loginCommand.getUserPw(), authInfo.getUserPw())) {
			result.rejectValue("userPw", "loginCommand.getUserPw()",
					"비밀번호가 틀렸습니다.");
			return "thymeleaf/login";
		}
		session.setAttribute("authInfo", authInfo);
		
		}
		return "thymeleaf/index";
		
	}
	
}
