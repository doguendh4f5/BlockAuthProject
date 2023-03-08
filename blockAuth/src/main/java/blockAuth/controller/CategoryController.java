package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blockAuth.service.category.ElectronicsSelectService;
import blockAuth.service.category.FashionSelectService;
import blockAuth.service.category.GrocerySelectService;

@Controller
@RequestMapping(value = "category")
public class CategoryController {	
	@Autowired
	FashionSelectService fashionSelectService;
	@Autowired
	ElectronicsSelectService electronicsSelectService;
	@Autowired
	GrocerySelectService grocerySelectService;
	
	@RequestMapping(value = "fashion")
	public String fashion(Model model) {
		fashionSelectService.execute(model);
		return "thymeleaf/category/fashionList";
	}
	
	@RequestMapping(value = "womanFashion")
	public String woman(Model model) {
		fashionSelectService.execute(model);
		return "thymeleaf/category/womanFashionList";
	}
	
	@RequestMapping(value = "manFashion")
	public String manFashion(Model model) {
		fashionSelectService.execute(model);
		return "thymeleaf/category/manFashionList";
	}
	
	@RequestMapping(value = "electronics")
	public String electronics(Model model) {
		electronicsSelectService.execute(model);
		return "thymeleaf/category/electronicsList";
	}
	
	@RequestMapping(value = "largeElectronics")
	public String largeElectronics(Model model) {
		electronicsSelectService.execute(model);
		return "thymeleaf/category/largeElectronicsList";
	}
	
	@RequestMapping(value = "smallElectronics")
	public String smallElectronics(Model model) {
		electronicsSelectService.execute(model);
		return "thymeleaf/category/smallElectronicsList";
	}
	
	@RequestMapping(value = "grocery")
	public String grocery(Model model) {
		grocerySelectService.execute(model);
		return "thymeleaf/category/groceryList";
	}
	
	@RequestMapping(value = "fruit")
	public String fruit(Model model) {
		grocerySelectService.execute(model);
		return "thymeleaf/category/fruitGroceryList";
	}
	
	@RequestMapping(value = "vegetable")
	public String vegetable(Model model) {
		grocerySelectService.execute(model);
		return "thymeleaf/category/vegetableGroceryList";
	}
	
	@RequestMapping(value = "meat")
	public String meat(Model model) {
		grocerySelectService.execute(model);
		return "thymeleaf/category/meatGroceryList";
	}
	
	@RequestMapping(value = "fish")
	public String fish(Model model) {
		grocerySelectService.execute(model);
		return "thymeleaf/category/fishGroceryList";
	}
}
