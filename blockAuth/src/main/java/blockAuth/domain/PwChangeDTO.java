package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pwChangeDTO")
public class PwChangeDTO {
	String userId;
	String userPw;
	String tableName;
	String userIdColumn;
	String userPwColumn;
}
