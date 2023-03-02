package blockAuth.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.mapper.InquireMapper;

@Service
public class SellerInqDelService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String adminInqNum) {
		inquireMapper.sellerAdminInqDel(adminInqNum);
	}
}
