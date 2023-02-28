package blockAuth.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.CartDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.CartMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartListService {
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(authInfo.getGrade().equals("seller")) {
			return "redirect:/";
		}
		BuyerDTO dto = buyerMapper.selectOneId(authInfo.getUserId());
		List<CartDTO> list = cartMapper.cartList(dto.getBuyerNum());
		model.addAttribute("list", list);
		return "thymeleaf/cart/cartList";
	}
}
