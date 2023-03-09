package blockAuth.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.PaymentDTO;
import blockAuth.mapper.PurchaseMapper;

@Service
public class PaymentSaveService {
	@Autowired
	PurchaseMapper purchaseMapper;
	
	public int execute(String purchaseNum, String paymentMethod, String paymentDate, String okDate, String cardCompany, String okNum, String creditNum) {
		String paymentNum = "BA-PM" + purchaseMapper.selectPayNum();
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setCardCompany(cardCompany);
		paymentDTO.setCreditNum(creditNum);
		paymentDTO.setOkDate(okDate);
		paymentDTO.setOkNum(okNum);
		paymentDTO.setPaymentDate(paymentDate);
		paymentDTO.setPaymentMethod(paymentMethod);
		paymentDTO.setPaymentNum(paymentNum);
		paymentDTO.setPurchaseNum(purchaseNum);
		int i = purchaseMapper.paymentInsert(paymentDTO);
		return i;
	}
}
