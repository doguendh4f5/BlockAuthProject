package blockAuth.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "votersDTO")
public class VotersDTO {
	int votersIdx;
	String voteNum;
	String buyerWalletAddr;
	Date voteDate;
	int candidatesIdx;
	String txId;
}
