package blockAuth.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.CartDTO;
import blockAuth.domain.CartGoodsDTO;
import blockAuth.mapper.BuyerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderSheetService {
	@Autowired
	BuyerMapper buyerMapper;
	public void execute(String [] prodCk, HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		BuyerDTO buyerDTO = buyerMapper.selectOneId(authInfo.getUserId());
		
		CartDTO cart = new CartDTO();
		cart.setGoodsNums(prodCk);
		cart.setBuyerNum(buyerDTO.getBuyerNum());
		
	}
}
