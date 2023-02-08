package blockAuth.service.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;

@Service
public class BuyerDetailService {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(String buyerNum, Model model) {
		BuyerDTO dto = buyerMapper.selectOne(buyerNum);
		model.addAttribute("buyerCommand", dto);
	}
}
