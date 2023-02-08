package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blockAuth.command.SellCommand;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;

@Service
public class SellerRegiService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SellerMapper sellerMapper;
	public Integer execute(SellCommand sellCommand) {
		String sellerPw = passwordEncoder.encode(sellCommand.getSellerPw());
		// HTML 폼에서 작성된 값 DTO에 저장하기
		SellerDTO seller = new SellerDTO();
		seller.setSellerNum(sellCommand.getSellerNum());
		seller.setSellerId(sellCommand.getSellerId());
		seller.setSellerPw(sellerPw);
		seller.setSellerName(sellCommand.getSellerName());
		seller.setSellerAddr1(sellCommand.getSellerAddr1());
		seller.setSellerAddr2(sellCommand.getSellerAddr2());
		seller.setSellerPostcode(sellCommand.getSellerPostcode());
		seller.setBusinessRegiNum(sellCommand.getBusinessRegiNum());
		seller.setSellerTel(sellCommand.getSellerTel());
		seller.setSellerWalletAddr(sellCommand.getSellerWalletAddr());
		seller.setMinDeliveryFee(sellCommand.getMinDeliveryFee());
		
		// 저장된 값 insert로 DB에 삽입
		Integer i = sellerMapper.sellerInsert(seller);
		System.out.println("판매자 정보 " + i + "개가 삽입되었습니다.");
		
		return i;
	}
}
