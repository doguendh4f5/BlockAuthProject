package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.mapper.BuyerMapper;

@Service
public class IdCheckService {
	@Autowired
	BuyerMapper buyerMapper;
	public String execute(String buyerId) {
		String buyId = buyerMapper.buyerIdCheck(buyerId);
		return buyId;
	}
}
