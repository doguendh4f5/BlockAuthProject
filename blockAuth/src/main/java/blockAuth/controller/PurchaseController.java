package blockAuth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inicis.std.util.SignatureUtil;

import blockAuth.service.mypage.BuyerMyInfoEditService;
import blockAuth.service.purchase.OrderSheetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderSheetService orderSheetService;
	@Autowired
	BuyerMyInfoEditService buyerMyInfoEditService;
	@RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model, HttpServletRequest request) {
		orderSheetService.execute(prodCk, session, model);
		buyerMyInfoEditService.execute(session, model);
		
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		try {
			String mKey = SignatureUtil.hash(signKey, "SHA-256");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
		String price				= "49";								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);

		try {
			String signature = SignatureUtil.makeSignature(signParam);
			System.out.println("===========================signature=======================  " + signature);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "pay/orderSheet2";
	}
	/*
	  @RequestMapping("orderSheet")
	public String orderSheet(
			@RequestParam(value = "prodCk")String [] prodCk,
			HttpSession session, Model model) {
		orderSheetService.execute(prodCk, session, model);
		return "thymeleaf/purchase/orderSheet";
	}
	 */
	
}
