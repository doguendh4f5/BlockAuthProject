package blockAuth.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BuyerMyInfoEditService {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		BuyerDTO buyer = buyerMapper.selectOneId(authInfo.getUserId());
		model.addAttribute("buyerCommand", buyer);
		model.addAttribute("membership", authInfo.getMembership());
	}
}
