package blockAuth.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.command.LoginCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CookieService {
	@Autowired
	LoginMapper loginMapper;
	public void execute(HttpServletRequest request, Model model) {
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().startsWith("id")) {
					LoginCommand loginCommand = new LoginCommand();
					loginCommand.setUserId(cookie.getValue());
					loginCommand.setIdSave(true);
					model.addAttribute("loginCommand", loginCommand);
				}
				if(cookie.getName().startsWith("auto")) {
					AuthInfo authInfo = loginMapper.loginSelect(cookie.getValue());
					HttpSession session = request.getSession();
					session.setAttribute("authInfo", authInfo);
				}
			}
		}else {
			model.addAttribute("loginCommand", new LoginCommand());
		}
	}
}
