package blockAuth.mapper;

import org.springframework.stereotype.Repository;

import blockAuth.domain.BuyerDTO;
import blockAuth.domain.SellerDTO;

@Repository(value="blockAuth.mapper.MyPageMapper")
public interface MyPageMapper {
	public Integer updateBuyerPw(BuyerDTO buyerDTO);
	public Integer updateSellerPw(SellerDTO sellerDTO);
}
