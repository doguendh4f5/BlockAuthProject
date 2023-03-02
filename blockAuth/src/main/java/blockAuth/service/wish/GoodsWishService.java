package blockAuth.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.WishDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishService {
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	WishMapper wishMapper;
	public String execute(String goodsNum, HttpSession session) {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		WishDTO dto = new WishDTO();
		dto.setGoodsNum(goodsNum);
		dto.setBuyerNum(buyerMapper.selectOneId(userId).getBuyerNum());
		
		wishMapper.wishAdd(dto);
		return wishMapper.wishCount(dto);
	}
}
