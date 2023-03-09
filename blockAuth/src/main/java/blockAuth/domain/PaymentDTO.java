package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "paymentDTO")
public class PaymentDTO {
	String paymentNum;
	String purchaseNum;
	String paymentMethod;
	String paymentDate;
	String okDate;
	String cardCompany;
	String okNum;
	String creditNum;
}
