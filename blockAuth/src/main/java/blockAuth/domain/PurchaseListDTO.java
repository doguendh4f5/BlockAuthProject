package blockAuth.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "purchaseListDTO")
public class PurchaseListDTO {
	String purchaseNum;
	String goodsNum;
	String buyerNum;
	int goodsQty;
	Date purchaseDate;
}
