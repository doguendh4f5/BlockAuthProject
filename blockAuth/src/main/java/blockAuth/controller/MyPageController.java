package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.command.PasswordChangeCommand;
import blockAuth.service.mypage.PasswordChangeService;
import blockAuth.service.mypage.PasswordCheckService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mypage")
public class MyPageController {
	@Autowired
	PasswordCheckService passwordCheckService;	//비밀번호 확인 서비스
	@Autowired
	PasswordChangeService passwordChangeService;	//비밀번호 변경 서비스
	
	// 마이페이지 첫 화면
	@RequestMapping("")
	public String mypage() {
		return "thymeleaf/mypage/mypage";
	}
	
	// 멤버십 구독 관련 페이지
	@RequestMapping("membershipInfo")
	public String memberShip() {
		return "thymeleaf/mypage/membership";
	}
	
	//비밀번호 변경 전 현재 비밀번호 확인 페이지
	@RequestMapping("memberPwCheck")
	public String memberPwCheck() {
		return "thymeleaf/mypage/memberPwCheck";
	}
	
	//비밀번호 변경 페이지
	@RequestMapping("memberPwChange")
	public String memberPwChange(@RequestParam(value = "userPw") String userPw
			, HttpSession session) {
		// 비밀번호 확인하는 코드 작성
		String str = passwordCheckService.execute(userPw, session);
		if(str == "비밀번호가 맞았습니다.") {
			return "thymeleaf/mypage/memberPwChange";
		}else {
			return "redirect:memberPwCheck";
		}
	}
	
	//비밀번호 변경 완료 페이지
	@RequestMapping("memberPwChangeComplete")
	public String memberPwChangeComplete(@Validated PasswordChangeCommand passwordChangeCommand
				, BindingResult result
				, HttpSession session) {
		if(!passwordChangeCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("buyerPw", "buyerCommand.buyerPw", 
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "redirect:memberPwChange";
		}
		Integer i = passwordChangeService.execute(passwordChangeCommand, session);
		if(i != null) {
			System.out.println("사용자의 비밀번호가 변경되었습니다.");
			return "redirect:../mypage";		
		}else {
			return "thymeleaf/mypage/memberPwChange";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
