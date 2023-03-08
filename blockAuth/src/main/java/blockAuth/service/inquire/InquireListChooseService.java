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
public class InquireListChooseService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	BuyerMapper buyerMapper;
	public String execute(Model model, HttpSession session, String str) {
		List<AdminInquireDTO> list = inquireMapper.inqList();
		model.addAttribute("list", list);
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		String value = "";
		
		if(str.equals("seller") && authInfo.getGrade().equals("admin")) {
			value = "0";
			model.addAttribute("value", value);
			return "thymeleaf/inquire/inquireListChoose";
		}else {
			value = "1";
			model.addAttribute("value", value);
			return "thymeleaf/inquire/inquireListChoose";
		}
	}
}
