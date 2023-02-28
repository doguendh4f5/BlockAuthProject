package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.service.VoteCandidateService;
import blockAuth.service.VoteListService;
import blockAuth.service.goods.GoodsListService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "vote")
public class VoteController {
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	VoteCandidateService voteCandidateService;
	@Autowired
	VoteListService voteListService;
	
	@RequestMapping("voteList")
	public String voteList(Model model) {
		voteListService.execute(model);
		return "thymeleaf/admin/voteList";
	}
	
	@RequestMapping("votePage")
	public String voteTest() {
		return "vote/votePage";
	}
	
	@RequestMapping("candidate")
	public String candidate(Model model) {
		goodsListService.execute(model);
		return "vote/candidate";
	}
	
	@RequestMapping("candidateOk")
	public void candidateOk(@RequestParam(value="name") String name,
			HttpServletResponse response) {
		voteCandidateService.execute(name, response);
	}
}
