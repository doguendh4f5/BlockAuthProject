package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "goodsWishDTO")
public class GoodsWishDTO {
	GoodsDTO goodsDTO;
	WishDTO wishDTO;
}
