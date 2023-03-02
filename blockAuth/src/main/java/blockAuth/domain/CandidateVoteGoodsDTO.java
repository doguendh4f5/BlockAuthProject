package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "candidateVoteGoodsDTO")
public class CandidateVoteGoodsDTO {
	CandidatesDTO candidatesDTO;
	VoteListDTO voteListDTO;
	GoodsDTO goodsDTO;
}
