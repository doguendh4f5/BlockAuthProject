package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blockAuth.command.PasswordChangeCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class PasswordChangeService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MyPageMapper myPageMapper;	
	public Integer execute(PasswordChangeCommand passwordChangeCommand, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String userPw = passwordEncoder.encode(passwordChangeCommand.getUserPw());
		BuyerDTO buyer = new BuyerDTO();
		SellerDTO seller = new SellerDTO();
		Integer i = 0;
		
		if(authInfo.getGrade().equals("buyer")) {
			//로그인 한 사용자 레벨이 buyer일 경우
			buyer.setBuyerId(authInfo.getUserId());
			buyer.setBuyerPw(userPw);
			i = myPageMapper.updateBuyerPw(buyer);
		}else if(authInfo.getGrade().equals("seller")) {
			//로그인 한 사용자 레벨이 seller일 경우
			seller.setSellerId(authInfo.getUserId());
			seller.setSellerPw(userPw);
			i = myPageMapper.updateSellerPw(seller);
		}
		authInfo.setUserPw(userPw);
		return i;
	}
}
