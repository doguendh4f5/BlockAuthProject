package blockAuth.mapper;

import org.springframework.stereotype.Repository;

@Repository(value = "blockAuth.mapper.ContractMapper")
public interface ContractMapper {
	public String contractNumGenerate();
}
