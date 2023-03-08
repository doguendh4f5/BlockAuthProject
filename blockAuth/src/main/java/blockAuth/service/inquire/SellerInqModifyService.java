package blockAuth.service.inquire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.command.InquireCommand;
import blockAuth.domain.AdminInquireDTO;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.InquireMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class SellerInqModifyService {
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand sellerInquireCommand, Model model, HttpSession session) {
		AdminInquireDTO inquire = new AdminInquireDTO();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		SellerDTO seller = new SellerDTO();
		BuyerDTO buyer = new BuyerDTO();
		if(authInfo.getGrade().equals("seller")) {
			seller = sellerMapper.selectNum(authInfo.getUserId());
			System.out.println("sellerNum = " + seller.getSellerNum() + "======================================");
		}else if(authInfo.getGrade().equals("buyer")) {
			buyer = buyerMapper.selectOneId(authInfo.getUserId());
		}
		
		inquire.setAdminInqNum(sellerInquireCommand.getAdminInqNum());
		inquire.setSellerNum(seller.getSellerNum());
		inquire.setBuyerNum(buyer.getBuyerNum());
		inquire.setAdminInqTitle(sellerInquireCommand.getAdminInqTitle());
		inquire.setAdminInqContent(sellerInquireCommand.getAdminInqContent());
		inquire.setAdminInqDate(sellerInquireCommand.getAdminInqDate());
		inquire.setAdminInqCategory(sellerInquireCommand.getAdminInqCategory());
		inquire.setAdminNum("admin");
		inquire.setAdminReplyContent(sellerInquireCommand.getAdminReplyContent());
		inquire.setAdminReplyDate(new Date());
		
		int i = inquireMapper.inquireUpdate(inquire);
	}
}
