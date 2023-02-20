package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.BuyerDTO;

@Repository(value = "blockAuth.mapper.BuyerMapper")
public interface BuyerMapper {
	public String buyerNumGenerate();
	public Integer buyerInsert(BuyerDTO dto);
	public List<BuyerDTO> selectAll();
	public BuyerDTO selectOne(String buyerNum);
	public Integer buyerUpdate(BuyerDTO dto);
	public Integer buyerDelete(String buyerNum);
	public String buyerIdCheck(String buyerId);
	public BuyerDTO selectOneId(String buyerId);
}
