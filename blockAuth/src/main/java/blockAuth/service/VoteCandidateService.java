package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.VoteCommand;
import blockAuth.domain.CandidatesDTO;
import blockAuth.mapper.VotingMapper;

@Service
public class VoteCandidateService {
	@Autowired
	VotingMapper votingMapper;
	public void execute(VoteCommand voteCommand) {
		for(String goodsNum : voteCommand.getGoodsName().split(",")) {
			CandidatesDTO dto = new CandidatesDTO();
			dto.setVoteNum(voteCommand.getVoteNum());
			dto.setGoodsName(goodsNum);
			votingMapper.insertCandidate(dto);
		}
	}
}
