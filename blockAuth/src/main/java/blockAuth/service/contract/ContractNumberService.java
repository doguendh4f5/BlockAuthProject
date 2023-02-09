package blockAuth.service.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.mapper.ContractMapper;

@Service
public class ContractNumberService {
	@Autowired
	ContractMapper contractMapper;
	public String execute() {
		String num = contractMapper.contractNumGenerate();
		return num;
	}
}
