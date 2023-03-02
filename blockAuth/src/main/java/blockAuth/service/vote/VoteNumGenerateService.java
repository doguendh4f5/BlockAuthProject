package blockAuth.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.command.VoteCommand;
import blockAuth.mapper.VotingMapper;

@Service
public class VoteNumGenerateService {
	@Autowired
	VotingMapper votingMapper;
	
	public void execute(VoteCommand voteCommand) {
		String num = votingMapper.generateVoteNum();
		voteCommand.setVoteNum(num);
	}
}
