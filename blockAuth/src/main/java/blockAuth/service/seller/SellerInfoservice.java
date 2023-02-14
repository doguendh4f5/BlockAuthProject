package blockAuth.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.ContractDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.ContractMapper;
import blockAuth.mapper.SellerMapper;

@Service
public class SellerInfoservice {
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	ContractMapper contractMapper;
	
	public void execute(String sellerNum, Model model) {
		SellerDTO seller = sellerMapper.selectOne(sellerNum);
		model.addAttribute("sellCommand", seller);
		ContractDTO contractDTO = contractMapper.selectContract(sellerNum);
		String metadata = contractDTO.getContractMetadata();
		model.addAttribute("metadata", metadata);
	}
}
