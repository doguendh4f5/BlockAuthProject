package blockAuth.mapper;

import org.springframework.stereotype.Repository;

import blockAuth.domain.WishDTO;

@Repository(value = "blockAuth.mapper.WishMapper")
public interface WishMapper {
	public Integer wishAdd(WishDTO dto);
	public String wishCount(WishDTO dto);
	
}
