package blockAuth.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.CartDTO;
import blockAuth.domain.CartGoodsDTO;
import blockAuth.domain.GoodsDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.GoodsMapper;
import blockAuth.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderSheetService2 {
	@Autowired
	BuyerMapper buyerMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	GoodsMapper goodsMapper;
	
	public long execute(HttpSession session, Model model, String goodsNum, String goodsStock) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		BuyerDTO buyerDTO = buyerMapper.selectOneId(authInfo.getUserId());
		
		GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsNum);
			
		Long goodsTotalPrice = 0L;
		goodsTotalPrice = goodsDTO.getGoodsPrice() * Integer.parseInt(goodsStock);

		Long goodsTotalDelivery = 0L;
		goodsTotalDelivery = goodsDTO.getGoodsDeliveryFee();

		String goodsNums = goodsNum;
		
		model.addAttribute("list", goodsDTO);
		model.addAttribute("goodsTotalPrice", goodsTotalPrice);
		model.addAttribute("goodsTotalDelivery", goodsTotalDelivery);
		model.addAttribute("goodsNums", goodsNums);
		model.addAttribute("goodsStock", goodsStock);
		
		long totalPrice = goodsTotalPrice + goodsTotalDelivery;
		
		return totalPrice;
		
	}
}
