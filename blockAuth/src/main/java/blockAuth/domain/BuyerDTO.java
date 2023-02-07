package blockAuth.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "buyerDTO")
public class BuyerDTO {
	String buyerNum;
	String buyerId;
	String buyerPw;
	String buyerName;
	String buyerPhone;
	String buyerAddr1;
	String buyerAddr2;
	Date buyerBirth;
	String buyerEmail;
	char membership;
	Long buyerPoint;
	String buyerPostcode;
	String buyerWalletAddr;
}
