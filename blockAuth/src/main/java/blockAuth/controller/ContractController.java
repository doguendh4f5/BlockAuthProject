package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blockAuth.domain.ContractDTO;
import blockAuth.mapper.ContractMapper;
import blockAuth.service.contract.ContractListService;

@Controller
@RequestMapping(value = "contract")
public class ContractController {
	@Autowired
	ContractMapper contractMapper;
	@Autowired
	ContractListService contractListService;

	@RequestMapping(value = "createContract")
	public @ResponseBody boolean createContract(
			@RequestParam(value = "contractNum") String contractNum,
			@RequestParam(value = "contractName") String contractName,
			@RequestParam(value = "sellerNum") String sellerNum,
			@RequestParam(value = "sellerWalletAddr") String sellerWalletAddr,
			@RequestParam(value = "contractDate") String contractDate,
			@RequestParam(value = "expiryDate") String expiryDate,
			@RequestParam(value = "metadata") String metadata
			) {
		ContractDTO contractDTO = new ContractDTO();
		contractDTO.setContractNum(contractNum);
		contractDTO.setContractName(contractName);
		contractDTO.setSellerNum(sellerNum);
		contractDTO.setSellerWalletAddr(sellerWalletAddr);
		contractDTO.setContractDate(contractDate);
		contractDTO.setExpiryDate(expiryDate);
		contractDTO.setContractMetadata(metadata);
		int i = contractMapper.createContract(contractDTO);
		if(i != 1) return false;
		return true;
	}
	
	@RequestMapping(value = "contractList")
	public String contractList(Model model) {
		contractListService.execute(model);
		return "thymeleaf/contract/contractList";
	}
	
	@RequestMapping(value = "contractInfo")
	public String contractInfo(@RequestParam(value = "contractNum") String contractNum,
								@RequestParam(value = "sellerWalletAddr") String sellerWalletAddr, Model model) {
		model.addAttribute("sellerWalletAddr", sellerWalletAddr);
		model.addAttribute("sellerWalletAddr", sellerWalletAddr);
		return "thymeleaf/contract/contractInfo";
	}
	
}
