package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "purchaseDTO")
public class PurchaseDTO {
	String purchaseNum;
	int totalPrice;
	String deliveryAddr;
	String deliveryAddr2;
	String deliveryPostcode;
	String receiverPhone;
	String receiverName;
	String buyerNum;
	String deliveryReq;
}
