package blockAuth.service.purchase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import blockAuth.domain.AuthInfo;
import blockAuth.domain.BuyerDTO;
import blockAuth.mapper.BuyerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class PaymentService {
	@Autowired
	BuyerMapper buyerMapper;
	
	public void execute(String totalPrice, String purchaseNum, Model model, HttpSession session) {
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		String mKey = "";
		String signature = "";
		
		try {
			mKey = SignatureUtil.hash(signKey, "SHA-256");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
		//String price				= "49";								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", totalPrice);
		signParam.put("timestamp", timestamp);

		try {
			signature = SignatureUtil.makeSignature(signParam);
			System.out.println("===========================signature=======================  " + signature);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooo : " + authInfo.getUserId());
		BuyerDTO buyerDTO = buyerMapper.selectOneId(authInfo.getUserId());
		String buyername = buyerDTO.getBuyerName();
		String buyertel = buyerDTO.getBuyerPhone();
		String buyerEmail = buyerDTO.getBuyerEmail();
		
		model.addAttribute("mid", mid);
		model.addAttribute("signKey", signKey);
		model.addAttribute("mKey", mKey);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("signature", signature);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("buyername", buyername);
		model.addAttribute("buyertel", buyertel);
		model.addAttribute("buyerEmail", buyerEmail);
		model.addAttribute("purchaseNum", purchaseNum);
		
	}
}
