package blockAuth.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blockAuth.command.GoodsCommand;
import blockAuth.mapper.GoodsMapper;

@Service
public class GoodsNumberService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand) {
		String num = goodsMapper.goodsNumGenerate();
		goodsCommand.setGoodsNum(num);
	}
}
