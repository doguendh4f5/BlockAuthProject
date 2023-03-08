package blockAuth.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.GoodsDTO;
import blockAuth.mapper.CategoryMapper;

@Service
public class GrocerySelectService {
	@Autowired
	CategoryMapper categoryMapper;
	public void execute(Model model) {
		List<GoodsDTO> list = categoryMapper.selectGrocery();
		List<GoodsDTO> list2 = categoryMapper.selectGroceryFruit();
		List<GoodsDTO> list3 = categoryMapper.selectGroceryVegetable();
		List<GoodsDTO> list4 = categoryMapper.selectGroceryMeat();
		List<GoodsDTO> list5 = categoryMapper.selectGroceryFish();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);
	}
}
