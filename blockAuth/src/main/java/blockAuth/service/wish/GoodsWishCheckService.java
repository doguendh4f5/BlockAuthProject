package blockAuth.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.WishDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishCheckService {
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	WishMapper wishMapper;
	public void execute(String goodsNum, HttpSession session, Model model) {
		if(session.getAttribute("authInfo") != null) {
			String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
			WishDTO dto = new WishDTO();
			dto.setGoodsNum(goodsNum);
			dto.setBuyerNum(buyerMapper.selectOneId(userId).getBuyerNum());
			model.addAttribute("wish", wishMapper.wishCount(dto));
		}else {
			model.addAttribute("wish", "0");
		}
	}
}
