package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("candidatesDTO")
public class CandidatesDTO {
	String voteNum;
	int idx;
	String goodsName;
	int vote;
}
