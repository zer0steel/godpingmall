package com.jyc.godpingmall.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jyc.godpingmall.service.CategoryService;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.util.AdminModelAndView;
import com.jyc.godpingmall.vo.Category;

@RestController
@RequestMapping(value = "/admin", produces = "application/json; charset=UTF-8")
public class CategoryController {
	
	@Autowired private CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public StatusCode addNewCategory(Category newCategory) {
		return categoryService.addNewCategory(newCategory);
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public AdminModelAndView getCategoryMap() {
		AdminModelAndView mav = new AdminModelAndView();
		mav.addObject("categoryMap", categoryService.getCategoryMap());
		return mav.setViewPage("category/categoryList");
	}
}
