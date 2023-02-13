package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blockAuth.command.SellCommand;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;

@Service
public class SellerJoinOkService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SellerMapper sellerMapper;
	Integer i;
	public Integer execute(SellCommand sellCommand) {
		SellerDTO dto = new SellerDTO();
		dto.setSellerAddr1(sellCommand.getSellerAddr1());
		dto.setSellerAddr2(sellCommand.getSellerAddr2());
		dto.setSellerId(sellCommand.getSellerId());
		dto.setSellerName(sellCommand.getSellerName());
		dto.setSellerNum(sellCommand.getSellerNum());
		dto.setSellerPostcode(sellCommand.getSellerPostcode());
		dto.setSellerPw(passwordEncoder.encode(sellCommand.getSellerPw()));
		dto.setSellerTel(sellCommand.getSellerTel());
		dto.setSellerWalletAddr(sellCommand.getSellerWalletAddr());
		dto.setMinDeliveryFee(sellCommand.getMinDeliveryFee());
		dto.setBusinessRegiNum(sellCommand.getBusinessRegiNum());
		i = sellerMapper.sellerInsert(dto);
		System.out.println(i + "개가 삽입되었습니다.");
		
		return i;
	}
	
}
