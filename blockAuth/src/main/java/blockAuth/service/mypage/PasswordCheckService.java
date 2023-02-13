package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class PasswordCheckService {
	@Autowired
	MyPageMapper myPageMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(String userPw, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String str = "";
		
		if(passwordEncoder.matches(userPw, authInfo.getUserPw())) {
			str =  "비밀번호가 맞았습니다.";
			System.out.println(str);
		}else {
			str =  "비밀번호가 틀렸습니다.";
			System.out.println(str);
		}
		return str;
	}
}
