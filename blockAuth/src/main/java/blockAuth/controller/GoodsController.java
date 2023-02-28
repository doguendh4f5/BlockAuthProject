package blockAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blockAuth.command.FileInfo;
import blockAuth.command.GoodsCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.GoodsDTO;
import blockAuth.service.goods.FileDelService;
import blockAuth.service.goods.GoodsDeleteService;
import blockAuth.service.goods.GoodsDetailService;
import blockAuth.service.goods.GoodsListService;
import blockAuth.service.goods.GoodsModifyService;
import blockAuth.service.goods.GoodsNumberService;
import blockAuth.service.goods.GoodsRegistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsNumberService goodsNumberService;	//상품번호자동생성
	@Autowired
	GoodsRegistService goodsRegistService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsModifyService goodsModifyService;
	
	//상품리스트 페이지
	@RequestMapping("goodsList")
	public String goodsList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}
	
	//상품정보입력페이지
	@RequestMapping(value = "goodsRegist", method = RequestMethod.GET)
	public String goodsRegist(GoodsCommand goodsCommand) {
		goodsNumberService.execute(goodsCommand);
		return "thymeleaf/goods/goodsForm";
	}
	
	//상품등록페이지
	@RequestMapping(value = "goodsRegist" , method = RequestMethod.POST)
	public String goodsRegist(GoodsCommand goodsCommand,
			HttpSession session) {
		/*if(result.hasErrors()) {
			return "thymeleaf/goods/goodsForm";
		}
		if(goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
			result.rejectValue("goodsMain", "goodsCommand.goodsMain", "대문이미지를 선택하여주세요.");
			return "thymeleaf/goods/goodsForm";
		}*/
		goodsRegistService.execute(goodsCommand, session);
		return "redirect:goodsList";
	}
	
	//상품상세페이지
	@RequestMapping("goodsDetail")
	public String goodsDetail(
			@RequestParam(value = "goodsNum") String goodsNum,
			Model model, HttpSession session) {
		model.addAttribute("newLineChar", '\n'); 
		goodsDetailService.execute(model, goodsNum);
		return "thymeleaf/goods/goodsDetail";
	}
	
	
	
	//상품수정정보입력페이지
	@RequestMapping(value = "goodsModify", method = RequestMethod.GET)
	public String goodsModify(
			@RequestParam(value = "goodsNum") String goodsNum, HttpSession session,
			Model model) {
		session.removeAttribute("fileList");
		goodsDetailService.execute(model, goodsNum);
		return "thymeleaf/goods/goodsUpdate";
	}
	
	//상품수정페이지
	@RequestMapping(value = "goodsModify", method = RequestMethod.POST)
	public String goodsModify(@Validated  GoodsCommand goodsCommand, 
			BindingResult result, HttpSession session, Model model) {
		goodsModifyService.execute( goodsCommand, session, result, model);
		if(result.hasErrors()) {
			GoodsDTO dto = goodsDetailService.execute(model, goodsCommand.getGoodsNum());
			model.addAttribute("goodsCommand", dto);
			model.addAttribute("error", "필수항목을 모두 입력해 주세요.");
			session.removeAttribute("fileList");
			return "thymeleaf/goods/goodsUpdate";
		}
		return "redirect:goodsDetail/"+goodsCommand.getGoodsNum();
	}
	
	//상품수정페이지에서 파일삭제
	@Autowired
	FileDelService fileDelService;
	@RequestMapping(value = "fileDel")
	public String fileDel(FileInfo fileInfo, HttpSession session, Model model) {
		fileDelService.execute(fileInfo, session, model);
		return "thymeleaf/goods/delPage";
	}
	
	//상품 1개 삭제
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@RequestMapping("goodsDelete/{goodsNum}")
	public String goodsDelete(
			@PathVariable(value = "goodsNum")String goodsNum
			, HttpServletRequest request) {
		goodsDeleteService.execute(goodsNum, request);
		return "redirect:../goodsList";		
	}
	
	
}
