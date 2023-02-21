package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import blockAuth.command.SellCommand;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;

@Component
@Service
public class SellerModifyService {
	@Autowired
	SellerMapper sellerMapper;
	public void execute(SellCommand sellCommand) {
		SellerDTO seller = new SellerDTO();
		seller.setSellerNum(sellCommand.getSellerNum());
		seller.setSellerTel(sellCommand.getSellerTel());
		seller.setSellerAddr1(sellCommand.getSellerAddr1());
		seller.setSellerAddr2(sellCommand.getSellerAddr2());
		seller.setSellerPostcode(sellCommand.getSellerPostcode());
		seller.setSellerWalletAddr(sellCommand.getSellerWalletAddr());
		seller.setMinDeliveryFee(sellCommand.getMinDeliveryFee());
		seller.setSellerEmail(sellCommand.getSellerEmail());
		int i = sellerMapper.sellerUpdate(seller);
		System.out.println("판매자 정보 " + i + "개가 수정되었습니다.");
	}
}
