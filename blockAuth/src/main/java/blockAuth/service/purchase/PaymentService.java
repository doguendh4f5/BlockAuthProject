package blockAuth.service.purchase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

@Service
public class PaymentService {
	public void execute(Long totalPrice, Model model) {
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
		signParam.put("price", Long.toString(totalPrice));
		signParam.put("timestamp", timestamp);

		try {
			signature = SignatureUtil.makeSignature(signParam);
			System.out.println("===========================signature=======================  " + signature);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		model.addAttribute("mid", mid);
		model.addAttribute("signKey", signKey);
		model.addAttribute("mKey", mKey);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("signature", signature);
		model.addAttribute("totalPrice", totalPrice);
	}
}
