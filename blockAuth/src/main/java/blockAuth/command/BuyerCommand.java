package blockAuth.command;





import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuyerCommand {
	String buyerNum;
	Long buyerPoint;
	char membership;
	@NotBlank(message = "아이디를 입력해주세요.")
	@Size(min = 4, max = 12)
	String buyerId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String buyerPw;
	@NotBlank(message = "비밀번호확인 입력해주세요.")
	String buyerPwCon;
	@NotBlank(message = "이름을 입력해주세요.")
	String buyerName;
	@NotBlank(message = "연락처를 입력해주세요.")
	String buyerPhone;
	@NotBlank(message = "주소를 입력해주세요.")
	String buyerAddr1;
	@NotBlank(message = "상세주소를 입력해주세요.")
	String buyerAddr2;
	@NotBlank(message = "우편번호를 입력해주세요.")
	String buyerPostcode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (message = "생년월일을 입력해주세요.")
	Date buyerBirth;
	@NotBlank
	@Email(message = "형식에 맞지 않습니다.")
	String buyerEmail;
	@NotBlank(message = "주소를 입력해주세요.")
	String buyerWalletAddr;
	
	public boolean isMemberPwEqualsMemberPwCon() {
		return buyerPw.equals(buyerPwCon);
	}
	
}
