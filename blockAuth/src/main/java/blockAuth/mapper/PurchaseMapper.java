package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.CartDTO;
import blockAuth.domain.CartGoodsDTO;
import blockAuth.domain.GoodsDTO;
import blockAuth.domain.PaymentDTO;
import blockAuth.domain.PurchaseDTO;
import blockAuth.domain.PurchaseListDTO;

@Repository(value = "blockAuth.mapper.PurchaseMapper")
public interface PurchaseMapper {
	public List<CartGoodsDTO> orderSheet(CartDTO cart);
	public String selectNum();
	public String selectPayNum();
	public Integer purchaseInsert(PurchaseDTO purchaseDTO);
	public Integer purchaseListInsert(PurchaseListDTO purchaseListDTO);
	public Integer cartGoodsDels(CartDTO cartDTO);
	public Integer paymentInsert(PaymentDTO paymentDTO);
}
