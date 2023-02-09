package blockAuth.service.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blockAuth.command.BuyerCommand;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerRegistService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	BuyerMapper buyerMapper;
	Integer i;
	public Integer execute(BuyerCommand buyerCommand) {
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
		dto.setBuyerPostcode(buyerCommand.getBuyerPostcode());
		dto.setBuyerPw(passwordEncoder.encode(buyerCommand.getBuyerPw()));
		dto.setBuyerWalletAddr(buyerCommand.getBuyerWalletAddr());
		dto.setMembership(buyerCommand.getMembership());
		i = buyerMapper.buyerInsert(dto);
		System.out.println(i + "개가 삽입되었습니다.");
		
		
		return i;
	}
}
