package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blockAuth.command.PasswordChangeCommand;
import blockAuth.service.mypage.BuyerMyInfoEditService;
import blockAuth.service.mypage.MypageDetailService;
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
	@Autowired
	MypageDetailService mypageDetailService;	//마이페이지 정보 가져오기
	@Autowired
	BuyerMyInfoEditService buyerMyInfoEditService;	//구매자 마이페이지 수정
	
	// 마이페이지 첫 화면
	@RequestMapping("")
	public String mypage(HttpSession session, Model model) {
		String url = mypageDetailService.execute(session, model);
		return url;
	}
	
	// 멤버십 구독 관련 페이지
	@RequestMapping("membershipInfo")
	public String memberShip() {
		return "thymeleaf/mypage/membership";
	}
	
	//비밀번호 변경 전 현재 비밀번호 확인 페이지
	@RequestMapping("memberPwCheck")
	public String memberPwCheck(PasswordChangeCommand passwordChangeCommand) {
		return "thymeleaf/mypage/memberPwCheck";
	}

	//비밀번호 변경 페이지
	@RequestMapping( value= "memberPwChange", method = RequestMethod.POST)
	public String memberPwChange(@Validated PasswordChangeCommand passwordChangeCommand
			, BindingResult result
			, HttpSession session) {
		//에러 체크
		if(result.hasErrors()) {
			return "thymeleaf/mypage/memberPwCheck";
		}
		// 비밀번호 확인하는 코드 작성
		String str = passwordCheckService.execute(passwordChangeCommand.getUserPw(), session);
		System.out.println(str);
		if(str == "비밀번호가 맞았습니다.") {
			return "thymeleaf/mypage/memberPwChange";
		}else {
			result.rejectValue("userPw", "PasswordChangeCommand.userPw", 
					"비밀번호가 틀렸습니다.");
			return "thymeleaf/mypage/memberPwCheck";
		}
	}
	
	//비밀번호 변경 완료 페이지
	@RequestMapping(value = "memberPwChangeComplete" , method = RequestMethod.POST)
	public String memberPwChangeComplete(@Validated PasswordChangeCommand passwordChangeCommand
				, BindingResult result
				, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/mypage/memberPwChange";
		}
		if(!passwordChangeCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("userPw", "PasswordChangeCommand.userPw", 
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/mypage/memberPwChange";
		}
		Integer i = passwordChangeService.execute(passwordChangeCommand, session);
		if(i != null) {
			System.out.println("사용자의 비밀번호가 변경되었습니다.");
			return "redirect:../mypage";
		}else {
			return "thymeleaf/mypage/memberPwChange";
		}
	}
		
	//마이페이지 수정
	@RequestMapping("buyerMyInfoEdit")
	public String myInfoEdit(HttpSession session, Model model) {
		buyerMyInfoEditService.execute(session, model);
		return "thymeleaf/mypage/buyerMyInfoEdit";
	}
	
	// 마이페이지 회원 탈퇴
	@RequestMapping("withdrawal")
	public String withdrawal() {
		return "thymeleaf/mypage/withdrawal";
	}
	
	// 마이페이지 찜한 목록
	@RequestMapping("wishList")
	public String wishList() {
		return "thymeleaf/mypage/wishList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
