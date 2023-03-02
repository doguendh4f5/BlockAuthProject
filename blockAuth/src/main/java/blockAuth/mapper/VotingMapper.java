package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.CandidateVoteGoodsDTO;
import blockAuth.domain.CandidatesDTO;
import blockAuth.domain.VoteListDTO;
import blockAuth.domain.VotersDTO;

@Repository(value = "blockAuth.mapper.VotingMapper")
public interface VotingMapper {
	public Integer insertCandidate(CandidatesDTO dto);
	public List<VoteListDTO> getVoteList();
	public String generateVoteNum();
	public Integer insertVoteInfo(VoteListDTO voteListDTO);
	public List<CandidateVoteGoodsDTO> selectVoteInfo(String voteNum);
	public Integer selectVotingSum();
	public String selectVoteImg(String voteNum);
	public Integer buyerVoteAccount(String account);
	public Integer candidatesUpdate(int idx);
	public Integer voteInsert(VotersDTO dto);
}
