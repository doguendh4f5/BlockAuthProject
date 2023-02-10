package blockAuth.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SellCommand {
	String sellerNum;
	@NotBlank(message = "5-8 글자의 아이디를 입력해주세요.")
	String sellerId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String sellerPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String sellerPwCon;
	@NotBlank(message = "이름을 입력해주세요.")
	String sellerName;
	@NotBlank(message = "연락처를 입력해주세요.")
	String sellerTel;
	@NotBlank(message = "주소를 입력해주세요.")
	String sellerAddr1;
	@NotBlank(message = "상세주소를 입력해주세요.")
	String sellerAddr2;
	@NotBlank(message = "우편번호를 입력해주세요.")
	String sellerPostcode;
	@NotBlank(message = "사업자등록번호를 입력해주세요.")
	String businessRegiNum;
	@NotBlank(message = "대표자의 지갑주소를 입력해주세요.")
	String sellerWalletAddr;
	@NotNull(message = "무료배송되는 최소구매금액을 입력해주세요.")
	Long minDeliveryFee;
	
	public boolean isSellerPwEqualsSellerPwCon() {
		return sellerPw.equals(sellerPwCon);
	}
}