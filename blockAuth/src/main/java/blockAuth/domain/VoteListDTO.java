package blockAuth.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "voteListDTO")
public class VoteListDTO {
	String voteNum;
	String voteName;
	String voteImg;
	String voteImgOrg;
	Date regiDate;
}
