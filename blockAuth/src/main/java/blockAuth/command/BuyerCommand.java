package blockAuth.command;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerCommand {
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
	String buyerPostCode;
	String buyerWalletAddr;
}
