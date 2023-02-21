package blockAuth.command;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Alias("findIdCommand")
public class FindIdCommand {
	@NotBlank(message = "등록한 전화번호를 입력해주세요.")
	String phone;
	@NotBlank(message = "등록한 이메일을 입력해주세요.")
	String email;
}
