package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.CartDTO;

@Repository(value = "blockAuth.mapper.CartMapper")
public interface CartMapper {
	public Integer cartAdd(CartDTO cart);
	public List<CartDTO> cartList(String buyerNum);
}
