package blockAuth.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AdminInquireDTO;
import blockAuth.mapper.InquireMapper;

@Service
public class InqDetailService {
	@Autowired
	InquireMapper inquireMapper;
	public AdminInquireDTO execute(Model model, String adminInqNum) {
		AdminInquireDTO inquire = inquireMapper.inqSelcetOne(adminInqNum);
		model.addAttribute("inquireCommand", inquire);
		return inquire;
	}
}
