package blockAuth.service.wish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.GoodsWishDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class WishListService {
	@Autowired
	WishMapper wishMapper;
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		BuyerDTO dto = buyerMapper.selectOneId(authInfo.getUserId());
		List<GoodsWishDTO> list = wishMapper.wishList(dto.getBuyerNum());
		model.addAttribute("list", list);
	}
}
