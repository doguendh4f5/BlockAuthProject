package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.mapper.BuyerMapper;

@Service
public class EmailCheckService {
	@Autowired
	BuyerMapper buyerMapper;
	public AuthInfo execute(String email) {
		AuthInfo authInfo = buyerMapper.buyerEmail(email);
		return authInfo;
	}
}
