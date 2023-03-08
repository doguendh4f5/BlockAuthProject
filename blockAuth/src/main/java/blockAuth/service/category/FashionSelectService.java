package blockAuth.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import blockAuth.domain.GoodsDTO;
import blockAuth.mapper.CategoryMapper;

@Service
public class FashionSelectService {
	@Autowired
	CategoryMapper categoryMapper;
	public void execute(Model model) {
		List<GoodsDTO> list = categoryMapper.selectFashion();
		List<GoodsDTO> list2 = categoryMapper.selectWomanFashion();
		List<GoodsDTO> list3 = categoryMapper.selectManFashion();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
	}
}
