package blockAuth.service.purchase;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.domain.CartDTO;
import blockAuth.domain.PurchaseDTO;
import blockAuth.domain.PurchaseListDTO;
import blockAuth.mapper.BuyerMapper;
import blockAuth.mapper.CartMapper;
import blockAuth.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class PurchaseService {
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	BuyerMapper buyerMapper;
	
	public String execute(HttpSession session, String totalPrice, String receiverName, 
						String deliveryAddr, String deliveryAddr2, String deliveryPostcode, 
						String receiverPhone, String deliveryReq, String goodsNums) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		BuyerDTO buyerDTO = buyerMapper.selectOneId(authInfo.getUserId());
		String memberNum = buyerDTO.getBuyerNum();
		String purchaseNum = "BA-P" + purchaseMapper.selectNum();
			
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		purchaseDTO.setBuyerNum(memberNum);
		purchaseDTO.setDeliveryAddr(deliveryAddr);
		purchaseDTO.setDeliveryAddr2(deliveryAddr2);
		purchaseDTO.setDeliveryPostcode(deliveryPostcode);
		purchaseDTO.setDeliveryReq(deliveryReq);
		purchaseDTO.setPurchaseNum(purchaseNum);
		purchaseDTO.setReceiverName(receiverName);
		purchaseDTO.setReceiverPhone(receiverPhone);
		purchaseDTO.setTotalPrice(Integer.parseInt(totalPrice));
		
		int i = purchaseMapper.purchaseInsert(purchaseDTO);
		
		if(i == 1) {
			for(String goodsNum : goodsNums.split("`")) {
				PurchaseListDTO purchaseListDTO = new PurchaseListDTO();
				purchaseListDTO.setBuyerNum(memberNum);
				purchaseListDTO.setGoodsNum(goodsNum);
				purchaseListDTO.setPurchaseNum(purchaseNum);
				purchaseMapper.purchaseListInsert(purchaseListDTO);
			}
			CartDTO cartDTO = new CartDTO();
			cartDTO.setGoodsNums(goodsNums.split("`"));
			cartDTO.setBuyerNum(memberNum);
			purchaseMapper.cartGoodsDels(cartDTO);
		}
		return purchaseNum;
	}
}
