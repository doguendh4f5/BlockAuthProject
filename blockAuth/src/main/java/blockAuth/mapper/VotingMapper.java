package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.CandidatesDTO;

@Repository(value = "blockAuth.mapper.VotingMapper")
public interface VotingMapper {
	public Integer insertCandidate(CandidatesDTO dto);
	public List<CandidatesDTO> getVoteList();
}
