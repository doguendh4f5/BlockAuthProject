package blockAuth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.CandidatesDTO;
import blockAuth.domain.VoteListDTO;
import blockAuth.mapper.VotingMapper;

@Service
public class VoteListService {
	@Autowired
	VotingMapper votingMapper;
	public void execute(Model model) {
		List<VoteListDTO> list = votingMapper.getVoteList();
		model.addAttribute("list", list);
	}
}
