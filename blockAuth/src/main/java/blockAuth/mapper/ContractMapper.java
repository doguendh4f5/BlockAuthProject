package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.ContractDTO;

@Repository(value = "blockAuth.mapper.ContractMapper")
public interface ContractMapper {
	public String contractNumGenerate();
	public Integer createContract(ContractDTO dto);
	public ContractDTO selectContract(String sellerNum);
	public List<ContractDTO> selectAllOfContract();
}
