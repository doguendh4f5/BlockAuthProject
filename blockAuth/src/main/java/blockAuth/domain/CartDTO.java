package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartDTO")
public class CartDTO {
	String goodsNum;
	String buyerNum;
	Long goodsStock;
	Long totalPrice;
	String [] goodsNums;
	Integer qty;
}
