package blockAuth.service.goods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import blockAuth.command.FileInfo;
import blockAuth.command.GoodsCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.GoodsDTO;
import blockAuth.domain.SellerDTO;
import blockAuth.mapper.GoodsMapper;
import blockAuth.mapper.SellerMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsModifyService {
	@Autowired
	SellerMapper sellerMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand,HttpSession session, BindingResult result, Model model) {
		GoodsDTO goods = new GoodsDTO();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		SellerDTO seller = sellerMapper.selectNum(authInfo.getUserId());
		
		goods.setGoodsNum(goodsCommand.getGoodsNum());
		goods.setSellerNum(seller.getSellerNum());
		goods.setGoodsName(goodsCommand.getGoodsName());
		goods.setGoodsPrice(goodsCommand.getGoodsPrice());
		goods.setGoodsContent(goodsCommand.getGoodsContent());
		goods.setGoodsSize(goodsCommand.getGoodsSize());
		goods.setGoodsStock(goodsCommand.getGoodsStock());
		goods.setGoodsCountry(goodsCommand.getGoodsCountry());
		goods.setGoodsCompany(goodsCommand.getGoodsCompany());
		goods.setGoodsRegiDate(goodsCommand.getGoodsRegiDate());
		goods.setGoodsDeliveryFee(goodsCommand.getGoodsDeliveryFee());
		goods.setModifyNum(seller.getSellerNum());
		
		
		List<FileInfo> list = (List<FileInfo>)session.getAttribute("fileList");
		
		// 삭제할 파일이 있는 경우 먼저 데이터베이스로 부터 파일 정보를 가지고와야 한다. 
		GoodsDTO lib = goodsMapper.goodsSelectOne(goodsCommand.getGoodsNum());
		
		String fileDir = "/view/goods/upload";
		String filePath=session.getServletContext().getRealPath(fileDir);
		
		if(!goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
			MultipartFile mf = goodsCommand.getGoodsMain();
			String originalFile = mf.getOriginalFilename();
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			String storeName = UUID.randomUUID().toString().replace("-", "");
			String storeFileName=storeName + extension;
			File file = new File(filePath + "/" + storeFileName);
			try {
				mf.transferTo(file); // 파일을 저장
			}catch(Exception e) {e.printStackTrace();}
			goods.setGoodsMainOrg(originalFile);
			goods.setGoodsMain(storeFileName);
		}else {// 파일 삭제를 했으나 파일이 upload되지 않은 경우
			if(list != null) {
				for(FileInfo fi : list ) {
					if(lib.getGoodsMainOrg().equals(fi.getOrgFile())) {
						result.reject("goodsMain", "대문이미지를 선택해주세요.");
						return;
					}
				}
			}
		}
		
		int i =goodsMapper.goodsUpdate(goods);
		
		// session에 있는 파일 삭제
		if(i > 0) {
			if(list != null) {
				for(FileInfo fi : list ) {
					File file = new File(filePath + "/" + fi.getStrFile());
					if(file.exists()) file.delete();
				}
			}
			
		}
	}
}
