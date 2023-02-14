package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "contractDTO")
public class ContractDTO {
	String contractNum;
	String contractName;
	String sellerNum;
	String sellerWalletAddr;
	String contractDate;
	String expiryDate;
	String contractMetadata;
}
