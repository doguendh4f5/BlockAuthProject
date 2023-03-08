package blockAuth.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.InquireCommand;
import blockAuth.domain.AdminInquireDTO;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.InquireMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BuyerInqRegiService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(InquireCommand inquireCommand
							, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		BuyerDTO buyer = buyerMapper.selectOneId(authInfo.getUserId());
		AdminInquireDTO inquire = new AdminInquireDTO();
		inquire.setAdminInqNum(inquireCommand.getAdminInqNum());
		inquire.setAdminInqDate(inquireCommand.getAdminInqDate());
		inquire.setAdminInqTitle(inquireCommand.getAdminInqTitle());
		inquire.setAdminInqContent(inquireCommand.getAdminInqContent());
		inquire.setAdminInqCategory(inquireCommand.getAdminInqCategory());
		inquire.setBuyerNum(buyer.getBuyerNum());
		
		int i = inquireMapper.insertInq(inquire);
		if(i == 1) {
			System.out.println("관리자 문의에 구매자가 문의했습니다");
		}
	}
}
