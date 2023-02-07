package blockAuth.service.buyer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerListService {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(Model model) {
		List<BuyerDTO> list = buyerMapper.selectAll();
		model.addAttribute("list", list);
	}
}
