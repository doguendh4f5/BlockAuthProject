package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.command.VoteCommand;
import blockAuth.domain.VotersDTO;
import blockAuth.mapper.VotingMapper;
import blockAuth.service.VoteCandidateService;
import blockAuth.service.VoteListService;
import blockAuth.service.goods.GoodsListService;
import blockAuth.service.vote.VoteInfoService;
import blockAuth.service.vote.VoteNumGenerateService;
import blockAuth.service.vote.VoteRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "vote")
public class VoteController {
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	VoteCandidateService voteCandidateService;
	@Autowired
	VoteListService voteListService;
	@Autowired
	VoteNumGenerateService voteNumGenerateService;
	@Autowired
	VoteRegistService voteRegistService;
	@Autowired
	VoteInfoService voteInfoService;
	
	@RequestMapping("voteList")
	public String voteList(Model model) {
		voteListService.execute(model);
		return "thymeleaf/vote/voteList";
	}
	
	@RequestMapping(value = "voteRegist", method = RequestMethod.GET)
	public String voteWrite(VoteCommand voteCommand) {
		voteNumGenerateService.execute(voteCommand);
		return "thymeleaf/vote/voteForm";
	}
	
	@RequestMapping(value = "voteRegist", method = RequestMethod.POST)
	public String voteRegist(VoteCommand voteCommand, HttpSession session) {
		voteRegistService.execute(voteCommand, session);
		voteCandidateService.execute(voteCommand);
		return "redirect:voteList";
	}
	
	@RequestMapping("votePage")
	public String voteTest(@RequestParam(value = "voteNum") String voteNum, Model model, HttpSession session) {
		voteInfoService.execute(voteNum, model, session);
		return "vote/votePage";
	}
	
	@RequestMapping("adminVotePage")
	public String adminVotePage(@RequestParam(value = "voteNum") String voteNum, Model model, HttpSession session) {
		voteInfoService.execute(voteNum, model, session);
		return "vote/votePage2";
	}
	
	@RequestMapping("candidate")
	public String candidate(Model model) {
		goodsListService.execute(model);
		return "vote/candidate";
	}
	
	@Autowired
	VotingMapper votingMapper;
	@ResponseBody
	@RequestMapping(value="voteWrite", method=RequestMethod.POST)
	public boolean voteWrite(@RequestParam(value="idx") int idx,
							 @RequestParam(value="voteNum") String voteNum,
							 @RequestParam(value="account") String account,
							 @RequestParam(value="tx_id") String tx_id) {
		Integer i = votingMapper.buyerVoteAccount(account);
		if(i != 1) {
			votingMapper.candidatesUpdate(idx);
			VotersDTO dto = new VotersDTO();
			dto.setBuyerWalletAddr(account);
			dto.setCandidatesIdx(idx);
			dto.setTxId(tx_id);
			dto.setVoteNum(voteNum);
			votingMapper.voteInsert(dto);
			System.out.println(account + "계정의 투표가 완료되었습니다.");
			return true;			
		}
		return false;
	}
	
}
