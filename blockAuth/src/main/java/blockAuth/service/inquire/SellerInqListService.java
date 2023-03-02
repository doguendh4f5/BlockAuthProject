package blockAuth.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AdminInquireDTO;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.InquireMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class SellerInqListService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	SellerMapper sellerMapper;
	public String execute(Model model, HttpSession session) {
		List<AdminInquireDTO> list = inquireMapper.inqList();
		model.addAttribute("list", list);
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		if(authInfo.getGrade().equals("seller")) {
			SellerDTO seller = new SellerDTO();
			seller = sellerMapper.selectNum(authInfo.getUserId());
			String sellerNum = seller.getSellerNum();
			model.addAttribute("sellerNum", sellerNum);
			return "thymeleaf/inquire/inquireListSeller";
		}else if(authInfo.getGrade().equals("buyer")) {
			return "thymeleaf/inquire/inquireList";
		}else{
			return "thymeleaf/inquire/inquireList";
		}
	}
}
