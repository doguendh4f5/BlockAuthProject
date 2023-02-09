package blockAuth.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "authInfo")
public class AuthInfo {
	String userId;
	String userPw;
	
	String buyerNum;
	String sellerNum;
	String grade;
}
