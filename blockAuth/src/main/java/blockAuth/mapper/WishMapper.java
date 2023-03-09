package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.GoodsDTO;
import blockAuth.domain.GoodsWishDTO;
import blockAuth.domain.WishDTO;

@Repository(value = "blockAuth.mapper.WishMapper")
public interface WishMapper {
	public Integer wishAdd(WishDTO dto);
	public String wishCount(WishDTO dto);
	public List<GoodsWishDTO> wishList(String buyerNum);
}
