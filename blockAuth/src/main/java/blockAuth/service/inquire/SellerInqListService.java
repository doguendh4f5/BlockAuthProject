package blockAuth.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AdminInquireDTO;
import blockAuth.mapper.InquireMapper;

@Service
public class SellerInqListService {
	@Autowired
	InquireMapper inquireMapper;
	
	public void execute(Model model) {
		List<AdminInquireDTO> list = inquireMapper.inqList();
		model.addAttribute("list", list);
	}
}
