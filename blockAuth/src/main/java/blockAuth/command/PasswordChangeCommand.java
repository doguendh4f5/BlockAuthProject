package blockAuth.command;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Alias(value="passwordChangeCommand")
public class PasswordChangeCommand {
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String userPw;
	@NotBlank(message = "비밀번호확인 입력해주세요.")
	String userPwCon;
	
	public boolean isMemberPwEqualsMemberPwCon() {
		return userPw.equals(userPwCon);
	}
}
