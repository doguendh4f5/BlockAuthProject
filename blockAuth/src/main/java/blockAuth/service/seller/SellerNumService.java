package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.SellCommand;
import blockAuth.mapper.SellerMapper;

@Service
public class SellerNumService {
	@Autowired
	SellerMapper sellerMapper;
	public void execute(SellCommand sellCommand) {
		String num = sellerMapper.sellerNumGenerate();
		sellCommand.setSellerNum(num);
	}
}
