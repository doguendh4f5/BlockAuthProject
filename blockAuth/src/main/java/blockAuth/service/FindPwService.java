package blockAuth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import blockAuth.command.FindPwCommand;
import blockAuth.domain.PwChangeDTO;
import blockAuth.mapper.FindMapper;

@Service
public class FindPwService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	FindMapper findMapper;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	SMSSendService smsSendService;
	
	public String execute(FindPwCommand findPwCommand,
			BindingResult result) {
		String s = findMapper.findPw(findPwCommand);
		if(s == null) {
			result.rejectValue("userId", "notFind",
					"아이디를 찾지 못했습니다.");
			return "thymeleaf/help/findPw";
		}else {
			String orgPw = UUID.randomUUID().toString().substring(0,8).concat("!");
			String newPw = passwordEncoder.encode(orgPw);
			PwChangeDTO dto = new PwChangeDTO();
			dto.setUserId(findPwCommand.getUserId());
			dto.setUserPw(newPw);
			if(s.equals("buyer")) {
				dto.setTableName("buyer");
				dto.setUserIdColumn("buyer_id");
				dto.setUserPwColumn("buyer_pw");
			}else if(s.equals("seller")) {
				dto.setTableName("seller");
				dto.setUserIdColumn("seller_id");
				dto.setUserPwColumn("seller_pw");
			}
			findMapper.changePw(dto);
			String content = "<html><body>"
							+ "안녕하세요 블록어스입니다. <br />'"
							+ findPwCommand.getUserId() + "'님의 "
							+ "임시 비밀번호는 <strong><b>["+ orgPw +"]</b></strong> 입니다. <br />"
							+ "반드시 로그인 후에 비밀번호를 변경해주세요."
							+ "</body></html>";
			String subject = "임시비밀번호";
			emailSendService.mailSend(content, subject, "administrator@gmail.com", findPwCommand.getUserEmail());
			content = "안녕하세요 블록어스입니다. " + findPwCommand.getUserId() + "'님의"
					+ "임시 비밀번호가 등록된 이메일로 전송되었습니다.";
			smsSendService.send("010-7146-1970", findPwCommand.getUserId(), content);
			
		}
		return "thymeleaf/help/findPwOk";
	}
}
