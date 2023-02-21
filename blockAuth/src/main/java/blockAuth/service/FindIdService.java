package blockAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import blockAuth.command.FindIdCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.mapper.FindMapper;

@Service
public class FindIdService {
	@Autowired
	FindMapper findMapper;
	public String execute(FindIdCommand findIdCommand,
			BindingResult result, Model model) {
		AuthInfo authInfo = findMapper.findId(findIdCommand.getEmail());
		if(authInfo == null) {
			result.rejectValue("email", "findIdCommand.email", "이메일이 틀렸습니다.");
			return "thymeleaf/help/findId";
		}else {
			if(!authInfo.getPhone().equals(findIdCommand.getPhone())) {
				result.rejectValue("phone", "findIdCommand.phone", "연락처가 틀렸습니다.");
				return "thymeleaf/help/findId";
			}
		}
		System.out.println("FindIdService(id) : " + authInfo.getUserId());
		model.addAttribute("userId", authInfo.getUserId());
		return "thymeleaf/help/findIdOk";
	}
}
