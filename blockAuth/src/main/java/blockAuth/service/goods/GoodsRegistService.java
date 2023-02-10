package blockAuth.service.goods;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import blockAuth.command.GoodsCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.GoodsDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.GoodsMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsRegistService {
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String userId = authInfo.getUserId();
		SellerDTO seller = sellerMapper.selectNum(userId);
		
		
		GoodsDTO goods = new GoodsDTO();
		goods.setGoodsNum(goodsCommand.getGoodsNum());
		goods.setSellerNum(seller.getSellerNum());
		goods.setGoodsName(goodsCommand.getGoodsName());
		goods.setGoodsPrice(goodsCommand.getGoodsPrice());
		goods.setGoodsContent(goodsCommand.getGoodsContent());
		goods.setGoodsSize(goodsCommand.getGoodsSize());
		goods.setGoodsStock(goodsCommand.getGoodsStock());
		goods.setGoodsCountry(goodsCommand.getGoodsCountry());
		goods.setGoodsCategory(goodsCommand.getGoodsCategory());
		goods.setGoodsCompany(goodsCommand.getGoodsCompany());
		goods.setGoodsRegiDate(goodsCommand.getGoodsRegiDate());
		goods.setGoodsDeliveryFee(goodsCommand.getGoodsDeliveryFee());
		goods.setWriterNum(seller.getSellerNum());

		/////////////////////////// 파일 추가 //////////////////////////////
		/// 파일 저장 경로 지정
		String fileDir = "/view/goods/upload";
		String filePath = session.getServletContext().getRealPath(fileDir);
		
		/// 파일이 업로드되었는 지 확인
		MultipartFile mf = goodsCommand.getGoodsMain(); // 012345678901
		String originalFile = mf.getOriginalFilename(); // test.png.png
		// .png
		String extension = originalFile.substring(originalFile.lastIndexOf("."));
		// UUID는 unique한 값을 가지고 올 때 주로 사용하는 API이다.
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension; // 4921hdifbsa.png
		
		File file = new File(filePath + "/" + storeFileName);
		try {
		mf.transferTo(file); // 파일저장
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		goods.setGoodsMain(storeFileName);
		goods.setGoodsMainOrg(originalFile);
		
		int i = goodsMapper.goodsInsert(goods);
		System.out.println(i + "개가 삽입되었습니다.");
	}
}
