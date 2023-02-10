package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.GoodsDTO;

@Repository("blockAuth.mapper.GoodsMapper")
public interface GoodsMapper {
	public String goodsNumGenerate();
	public Integer goodsInsert(GoodsDTO goods);
	public List<GoodsDTO> goodsList();
	public GoodsDTO goodsSelectOne(String goodsNum);
	public Integer goodsUpdate(GoodsDTO goods);
	public Integer goodsDelete(String goodsNum);
}
