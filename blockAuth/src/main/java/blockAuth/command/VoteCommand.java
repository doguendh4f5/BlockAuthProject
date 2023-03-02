package blockAuth.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class VoteCommand {
	String voteNum;
	String voteName;
	MultipartFile voteImg;
	String goodsName;
}
