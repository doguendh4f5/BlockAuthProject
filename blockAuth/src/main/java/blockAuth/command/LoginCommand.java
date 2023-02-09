package blockAuth.command;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Alias(value = "login")
public class LoginCommand {
	@NotBlank(message = "아이디를 입력해주세요.")
	String userId;
	@NotBlank(message = "비밀번호를 입력해주세요.")
	String userPw;
	
	String autoLogin;
	String idSave;
}
