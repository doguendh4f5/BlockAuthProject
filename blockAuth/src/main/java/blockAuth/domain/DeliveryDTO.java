package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "deliveryDTO")
public class DeliveryDTO {
	String receiverName;
	String deliveryAddr;
	String deliveryAddr2;
	String deliveryPostcode;
	String receiverPhone;
	String deliveryReq;
}
