package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class SellerMyInfoEditService {
	@Autowired
	SellerMapper sellerMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		SellerDTO seller = sellerMapper.selectNum(authInfo.getUserId());
		model.addAttribute("sellCommand", seller);
	}
}
