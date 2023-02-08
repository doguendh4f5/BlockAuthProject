package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.mapper.SellerMapper;

@Service
public class SellerDeleteService {
	@Autowired
	SellerMapper sellerMapper;
	public void execute(String sellerNum) {
		sellerMapper.sellerDelete(sellerNum);
	}
}
