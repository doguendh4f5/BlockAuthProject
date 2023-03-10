package blockAuth.service.inquire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.InquireCommand;
import blockAuth.domain.AdminInquireDTO;
import blockAuth.mapper.InquireMapper;

@Service
public class SellerInqNumberService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand inquireCommand) {
		String num = inquireMapper.sellerInqNumGenerate();
		inquireCommand.setAdminInqNum(num);
		inquireCommand.setAdminInqDate(new Date());
	}
}
