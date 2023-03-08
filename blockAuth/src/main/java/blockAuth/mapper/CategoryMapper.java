package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.GoodsDTO;

@Repository(value = "blockAuth.mapper.CategoryMapper")
public interface CategoryMapper {
	public List<GoodsDTO> selectFashion();
	public List<GoodsDTO> selectWomanFashion();
	public List<GoodsDTO> selectManFashion();
	public List<GoodsDTO> selectElectronics();
	public List<GoodsDTO> selectLargeElectronics();
	public List<GoodsDTO> selectSmallElectronics();
	
	public List<GoodsDTO> selectGrocery();
	public List<GoodsDTO> selectGroceryFruit();
	public List<GoodsDTO> selectGroceryVegetable();
	public List<GoodsDTO> selectGroceryMeat();
	public List<GoodsDTO> selectGroceryFish();
}
