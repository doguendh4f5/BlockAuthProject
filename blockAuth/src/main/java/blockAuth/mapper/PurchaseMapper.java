package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.CartDTO;
import blockAuth.domain.CartGoodsDTO;

@Repository(value = "blockAuth.mapper.PurchaseMapper")
public interface PurchaseMapper {
	public List<CartGoodsDTO> orderSheet(CartDTO cart);
	
}
