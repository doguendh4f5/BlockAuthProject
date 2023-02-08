package blockAuth.service.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.BuyerCommand;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerModifyService {
	@Autowired
	BuyerMapper buyerMapper;
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
		dto.setBuyerPostcode(buyerCommand.getBuyerPostcode());
		int i = buyerMapper.buyerUpdate(dto);
		System.out.println(i + "개가 수정되었습니다.");
	}
}
