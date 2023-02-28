package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MembershipRegistService {
	@Autowired
	BuyerMapper buyerMapper;
	
	public void execute(HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		buyerMapper.membershipRegist(authInfo.getUserId());
	}
}
