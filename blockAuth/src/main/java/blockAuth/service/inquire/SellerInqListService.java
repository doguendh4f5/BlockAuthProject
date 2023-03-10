package blockAuth.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AdminInquireDTO;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.InquireMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class SellerInqListService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	BuyerMapper buyerMapper;
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
			BuyerDTO buyer = new BuyerDTO();
			buyer = buyerMapper.selectOneId(authInfo.getUserId());
			String buyerNum = buyer.getBuyerNum();
			model.addAttribute("buyerNum", buyerNum);
			return "thymeleaf/inquire/inquireListBuyer";
		}else{
			return "thymeleaf/inquire/inquireList";
		}
	}
}
