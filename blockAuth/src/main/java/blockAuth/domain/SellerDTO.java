package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("sellerDTO")
public class SellerDTO {
	String sellerNum;
	String sellerId;
	String sellerPw;
	String sellerName;
	String sellerAddr1;
	String sellerAddr2;
	String sellerPostcode;
	String businessRegiNum;
	String sellerTel;
	String sellerWalletAddr;
	Long minDeliveryFee;
	String sellerEmail;
	String bank;
	String accountNum;
}