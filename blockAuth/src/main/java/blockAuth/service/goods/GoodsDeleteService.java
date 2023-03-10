package blockAuth.service.goods;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.domain.GoodsDTO;
import blockAuth.mapper.GoodsMapper;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, HttpServletRequest request) {
		GoodsDTO dto = goodsMapper.goodsSelectOne(goodsNum);
		Integer i = goodsMapper.goodsDelete(goodsNum);
		System.out.println(i + "개가 삭제되었습니다.");
		if(i > 0 ) {
			String fileDir = "/view/goods/upload";
			String filePath=request.getServletContext().getRealPath(fileDir);
			//메인 이미지 삭제
			String fileName = dto.getGoodsMain();
			File file = new File(filePath + "/" +fileName);
			if(file.exists())file.delete();
		}
	}
}