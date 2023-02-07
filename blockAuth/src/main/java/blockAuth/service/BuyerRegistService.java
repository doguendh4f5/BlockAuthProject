package blockAuth.service;

import org.springframework.stereotype.Service;

import blockAuth.command.BuyerCommand;
import blockAuth.domain.BuyerDTO;

@Service
public class BuyerRegistService {
	public void execute(BuyerCommand buyerCommand) {
		BuyerDTO dto = new BuyerDTO();
		dto.setBuyerAddr1(buyerCommand.getBuyerAddr1());
		dto.setBuyerAddr2(buyerCommand.getBuyerAddr2());
		dto.setBuyerBirth(buyerCommand.getBuyerBirth());
		dto.setBuyerEmail(buyerCommand.getBuyerEmail());
		dto.setBuyerId(buyerCommand.getBuyerId());
		dto.setBuyerName(buyerCommand.getBuyerName());
		dto.setBuyerNum(buyerCommand.getBuyerNum());
		dto.setBuyerPhone(buyerCommand.getBuyerPhone());
		dto.setBuyerPoint(buyerCommand.getBuyerPoint());
		dto.setBuyerPostCode(buyerCommand.getBuyerPostCode());
		dto.setBuyerPw(buyerCommand.getBuyerPw());
		dto.setBuyerWalletAddr(buyerCommand.getBuyerWalletAddr());
		dto.setMembership(buyerCommand.getMembership());
		
	}
}
