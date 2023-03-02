package blockAuth.service.vote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.CandidateVoteGoodsDTO;
import blockAuth.mapper.VotingMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class VoteInfoService {
	@Autowired
	VotingMapper votingMapper;
	
	public void execute(String voteNum, Model model, HttpSession session) {
		List<CandidateVoteGoodsDTO> list = votingMapper.selectVoteInfo(voteNum);
		model.addAttribute("lists", list);
		int sum = votingMapper.selectVotingSum();
		model.addAttribute("sum", sum);
		String voteImg = votingMapper.selectVoteImg(voteNum);
		model.addAttribute("voteImg", voteImg);
		
		
	}
}
