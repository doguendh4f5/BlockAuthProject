package blockAuth.mapper;

import org.springframework.stereotype.Repository;

@Repository(value = "blockAuth.mapper.BuyerMapper")
public interface BuyerMapper {
	public String buyerNumGenerate();
}
