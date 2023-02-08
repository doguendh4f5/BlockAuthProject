package blockAuth.service.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerDeleteService {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(String buyerNum) {
		buyerMapper.buyerDelete(buyerNum);
	}
}
