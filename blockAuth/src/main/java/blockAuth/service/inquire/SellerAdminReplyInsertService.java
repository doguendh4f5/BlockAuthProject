package blockAuth.service.inquire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AdminInquireDTO;
import blockAuth.mapper.InquireMapper;

@Service
public class SellerAdminReplyInsertService {
	@Autowired
	InquireMapper inquireMapper;
	public String execute(String adminReplyContent, String adminInqNum) {
		AdminInquireDTO inquire = new AdminInquireDTO();
		
		inquire.setAdminReplyContent(adminReplyContent);
		inquire.setAdminInqNum(adminInqNum);
		inquire.setAdminReplyDate(new Date());
		int i = inquireMapper.sellerReplyUpdate(inquire);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ " + i);
		return Integer.toString(i);
	}
}
