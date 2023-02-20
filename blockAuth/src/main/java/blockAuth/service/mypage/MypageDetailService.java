package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MypageDetailService {
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	BuyerMapper buyerMapper;
	public String execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String grade = authInfo.getGrade();
		if(grade.equals("seller")) {
			SellerDTO seller = sellerMapper.selectNum(authInfo.getUserId());
			model.addAttribute("sellCommand", seller);
			return "thymeleaf/mypage/sellerMypage";
		}else {
			BuyerDTO buyer = buyerMapper.selectOneId(authInfo.getUserId());
			model.addAttribute("buyerCommand", buyer);
			return "thymeleaf/mypage/buyerMypage";
		}
	}
}
