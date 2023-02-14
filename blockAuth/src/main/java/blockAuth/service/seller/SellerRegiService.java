package blockAuth.service.seller;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import blockAuth.command.SellCommand;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.SellerMapper;
import blockAuth.service.contract.ContractNumberService;

@Service
public class SellerRegiService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ContractNumberService contractNumberService;
	
	@Autowired
	SellerMapper sellerMapper;
	public Integer execute(SellCommand sellCommand) {
		String sellerPw = passwordEncoder.encode(sellCommand.getSellerPw());
		// HTML 폼에서 작성된 값 DTO에 저장하기
		SellerDTO seller = new SellerDTO();
		seller.setSellerNum(sellCommand.getSellerNum());
		seller.setSellerId(sellCommand.getSellerId());
		seller.setSellerPw(sellerPw);
		seller.setSellerName(sellCommand.getSellerName());
		seller.setSellerAddr1(sellCommand.getSellerAddr1());
		seller.setSellerAddr2(sellCommand.getSellerAddr2());
		seller.setSellerPostcode(sellCommand.getSellerPostcode());
		seller.setBusinessRegiNum(sellCommand.getBusinessRegiNum());
		seller.setSellerTel(sellCommand.getSellerTel());
		seller.setSellerWalletAddr(sellCommand.getSellerWalletAddr());
		seller.setMinDeliveryFee(sellCommand.getMinDeliveryFee());
		
		// 저장된 값 insert로 DB에 삽입
		Integer i = sellerMapper.sellerInsert(seller);
		System.out.println("판매자 정보 " + i + "개가 삽입되었습니다.");
		
		return i;
	}
	
	// pdf 파일 생성
	public void createPdf(SellCommand sellCommand, Model model) {
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String now = today.format(formatter);
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyMMdd");
		String now2 = today.format(formatter2);
		LocalDate finDay = today.plusYears(2);
		String expDay = finDay.format(formatter);
		
		String fileName = now2 + "_" + sellCommand.getSellerName() + "_계약서사본.pdf";
		String contractNum = contractNumberService.execute();
		String contractName = now2 + " " + sellCommand.getSellerName() + " 전자상거래입점계약서";
		
		
		String result = ""; 
		String fileURL = "src/main/resources/static/contTempFiles/";
		try {
			Document document = new Document(); // pdf문서를 처리하는 객체
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileURL + fileName));
	
			document.open(); 
			BaseFont baseFont = BaseFont.createFont("src/main/resources/static/font/NanumBarunGothic.ttf", BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
			
			Font font = new Font(baseFont, 10);
			Font font2 = new Font(baseFont, 17);
			
			String signature = "src/main/resources/static/images/logo.png";
			Image sign = Image.getInstance(signature);
			sign.scalePercent(50);
			sign.setAbsolutePosition(320, 130);
			
			Chunk chunk = new Chunk("\n전자상거래입점계약서", font2); // 타이틀 객체
			
			Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph); 
 
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); 
            
            Chunk chunk1 = new Chunk("1. 계약번호 : " + contractNum, font);
            Chunk chunk2 = new Chunk("2. 계약명 : " + contractName, font);
            Chunk chunk3 = new Chunk("3. 계약자 지갑주소 : " + sellCommand.getSellerWalletAddr(), font);
            Chunk chunk15 = new Chunk("4. 계약자 계좌정보 : " + sellCommand.getBank() + " " + sellCommand.getAccountNum(), font);
            
            Paragraph ph1 = new Paragraph(chunk1);
            Paragraph ph2 = new Paragraph(chunk2);
            Paragraph ph3 = new Paragraph(chunk3);
            Paragraph ph15 = new Paragraph(chunk15);
            
            document.add(ph1);
            document.add(ph2);
            document.add(ph3);
            document.add(ph15);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk4 = new Chunk("블록어스(사업자등록번호 1234567890, 이하\"갑\")와 " + sellCommand.getSellerName() 
            						 + "(사업자등록번호 " + sellCommand.getBusinessRegiNum() + ", 이하\"을\")는 전자상거래 사이트 사용과 관련하여 다음과 같이 합의한다.", font);
            
            Paragraph ph4 = new Paragraph(chunk4);
            document.add(ph4);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk5 = new Chunk("다음", font);
            
            Paragraph ph5 = new Paragraph(chunk5);
            ph5.setAlignment(Element.ALIGN_CENTER);
            document.add(ph5);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk6 = new Chunk("제1조(계약의 목적) 을은 갑이 소유한 블록어스 사이트의 입점 및 판매를 위해 이 계약을 체결한다.", font);
            
            Paragraph ph6 = new Paragraph(chunk6);
            
            document.add(ph6);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk7 = new Chunk("제2조(용어의 정리)\n"
            						+ "1. \"입점\"이라 함은 블록어스 사이트 내에서 물건을 판매할 권리를 말한다.\n"
            						+ "2. \"노출\"이라 함은 블록어스 사이트 내의 검색 결과에 을의 상품이 검색되는 것을 말한다.", font);
            
            Paragraph ph7 = new Paragraph(chunk7);
            document.add(ph7);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk8 = new Chunk("제3조(갑과 을의 의무)\n"
            						+ "① 갑은 블록어스 입점을 을에게 무상으로 사용할 수 있도록 제공한다.\n"
            						+ "② 입점의 제공조건은 다음과 같다.\n"
            						+ "	가. 기간 : " + now + "부터 " + expDay + "까지\n"
            						+ "	나. 용도 : 을의 물건 판매 및 노출\n"
            						+ "③ 을은 블록어스를 입점 및 소비자의 상품 문의 외 용도로 사용할 수 없다\n"
            						+ "④ 을은 블록어스 입점을 제 3자에게 사용하도록 권한을 이전하여서는 안 된다", font);
            
            Paragraph ph8 = new Paragraph(chunk8);
            document.add(ph8);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk9 = new Chunk("제 4조(수익 배분) 달을 기준으로 을에게 결제된 금액은 매월 25일 갑과 을이 각각 4할, 6할로 나누어 갖는다", font);
            
            Paragraph ph9 = new Paragraph(chunk9);
            document.add(ph9);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk10 = new Chunk("제 5조(계약의 해지)\n"
            						+ "① 갑은 계약기간이 시작되고서 1년이 지나면 언제든지 계약을 해지할 수 있다.\n"
            						+ "다만 갑이 을에게 계약 해지의 의사를 표시한 때부터 3개월 이후에 계약 해지의 효력이 발생한다.\n"
            						+ "② 을은 갑이 계약 해지의 의사를 표시한 때로부터 3개월 이내에 입점 해지를 하여야 하고,\n"
            						+ "그렇지 않으면 3개월이 지난 때부터 월 100만원의 손해배상금을 지급한다.", font);
            
            Paragraph ph10 = new Paragraph(chunk10);
            document.add(ph10);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk11 = new Chunk("제 6조(관할법원) 이 계약과 발생하는 당사자 간의 모든 분쟁은 서울중앙지방법원이 담당한다.", font);
            
            Paragraph ph11 = new Paragraph(chunk11);
            document.add(ph11);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
    		
            Chunk chunk12 = new Chunk(now, font);
            
            Paragraph ph12 = new Paragraph(chunk12);
            ph12.setAlignment(Element.ALIGN_CENTER);
            document.add(ph12);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk13 = new Chunk("갑   (주)블록어스   (인)", font);
            
            Paragraph ph13 = new Paragraph(chunk13);
            ph13.setAlignment(Element.ALIGN_CENTER);
            document.add(ph13);
            document.add(sign);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk16 = new Chunk("(서울특별시 영등포구 양평동3가 15-1 월드메르디앙비즈센터 4층 401 402호)", font);
            
            Paragraph ph16 = new Paragraph(chunk16);
            ph16.setAlignment(Element.ALIGN_CENTER);
            document.add(ph16);
            document.add(Chunk.NEWLINE);
            
            Chunk chunk14 = new Chunk("을   " + sellCommand.getSellerName() + "   (인)\n"
            						+ sellCommand.getSellerAddr1() + " " + sellCommand.getSellerAddr2(), font);
            
            Paragraph ph14 = new Paragraph(chunk14);
            ph14.setAlignment(Element.ALIGN_CENTER);
            document.add(ph14);
            document.add(Chunk.NEWLINE);
            
            document.close(); // 저장이 끝났으면 document객체를 닫는다.
            result = "pdf 파일이 생성되었습니다.";
    
            // fileDownload.fileDownLoad(fileURL, fileName, fileName, request, response);
            
		}catch(Exception e) {
			 e.printStackTrace();
	         result = "pdf 파일 생성에 실패하였습니다.";
		}
		model.addAttribute("sellerWalletAddr", sellCommand.getSellerWalletAddr()); 
		model.addAttribute("contractNum", contractNum);
		model.addAttribute("contractName", contractName);
		model.addAttribute("contractDate", now);
		model.addAttribute("expiryDate", expDay);
		model.addAttribute("sellerNum", sellCommand.getSellerNum());
		model.addAttribute("fileName", fileName);
	}
}
