package blockAuth.service.inquire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.SellerInquireCommand;
import blockAuth.domain.AdminInquireDTO;
import blockAuth.mapper.InquireMapper;

@Service
public class SellerInqNumberService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(SellerInquireCommand sellerInquireCommand) {
		String num = inquireMapper.sellerInqNumGenerate();
		sellerInquireCommand.setAdminInqNum(num);
		sellerInquireCommand.setAdminInqDate(new Date());
	}
}
