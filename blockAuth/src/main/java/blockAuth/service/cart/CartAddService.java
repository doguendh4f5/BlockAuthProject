package blockAuth.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.CartDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.CartMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartAddService {
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(String goodsNum, Long goodsStock, HttpSession session) {
		System.out.println(goodsNum);
		CartDTO cart = new CartDTO();
		cart.setGoodsNum(goodsNum);
		cart.setGoodsStock(goodsStock);
		cart.setBuyerNum(buyerMapper.selectOneId(
						((AuthInfo)session.getAttribute("authInfo"))
						.getUserId())
						.getBuyerNum());
		
		return cartMapper.cartAdd(cart).toString();
	}
}
