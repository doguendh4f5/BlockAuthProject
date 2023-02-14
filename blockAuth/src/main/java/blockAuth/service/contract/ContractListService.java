package blockAuth.service.contract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.ContractDTO;
import blockAuth.mapper.ContractMapper;

@Service
public class ContractListService {
	@Autowired
	ContractMapper contractMapper;
	
	public void execute(Model model) {
		List<ContractDTO> list = contractMapper.selectAllOfContract();
		model.addAttribute("list", list);
		
	}
}
