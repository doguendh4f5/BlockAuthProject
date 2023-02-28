package blockAuth.service;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.CandidatesDTO;
import blockAuth.mapper.VotingMapper;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class VoteCandidateService {
	@Autowired
	VotingMapper votingMapper;
	public void execute(String name, HttpServletResponse response) {
		CandidatesDTO dto = new CandidatesDTO();
		dto.setGoodsName(name);
		votingMapper.insertCandidate(dto);
		try {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String str=  "<script language='javascript'>" 
				  +  " opener.document.location.reload();"
		          +  " window.self.close();"
		          +  "</script>";
		out.print(str);
		out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
