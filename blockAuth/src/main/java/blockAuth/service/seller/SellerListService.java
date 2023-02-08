package blockAuth.service.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;

@Service
public class SellerListService {
	@Autowired
	SellerMapper sellerMapper;
	public void execute(Model model) {
		List<SellerDTO> list = sellerMapper.selectAll();
		model.addAttribute("list", list);
	}
}
