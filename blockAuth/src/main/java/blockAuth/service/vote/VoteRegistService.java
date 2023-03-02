package blockAuth.service.vote;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import blockAuth.command.VoteCommand;
import blockAuth.domain.VoteListDTO;
import blockAuth.mapper.VotingMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class VoteRegistService {
	@Autowired
	VotingMapper votingMapper;
	
	public void execute(VoteCommand voteCommand, HttpSession session) {
		
		String fileDir = "/view/voteImg";
		String filePath = session.getServletContext().getRealPath(fileDir);

		MultipartFile mf = voteCommand.getVoteImg(); 
		String originalFile = mf.getOriginalFilename(); 

		String extension = originalFile.substring(originalFile.lastIndexOf("."));

		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension; 

		File file = new File(filePath + "/" + storeFileName);
		try {
			mf.transferTo(file); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		VoteListDTO voteListDTO = new VoteListDTO();
		voteListDTO.setVoteNum(voteCommand.getVoteNum());
		voteListDTO.setVoteName(voteCommand.getVoteName());
		voteListDTO.setVoteImg(storeFileName);
		voteListDTO.setVoteImgOrg(originalFile);

		votingMapper.insertVoteInfo(voteListDTO);
		
	}
}
