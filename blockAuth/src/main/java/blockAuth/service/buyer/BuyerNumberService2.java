package blockAuth.service.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.BuyerCommand;
import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerNumberService2 {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(BuyerCommand buyerCommand) {
		String num = buyerMapper.buyerNumGenerate();
		buyerCommand.setBuyerNum(num);
	}
}
