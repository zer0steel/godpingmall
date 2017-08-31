package com.jyc.godpingmall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.CategoryChecker;
import com.jyc.godpingmall.vo.Category;

@Service
public class CategoryService {
	
	@Autowired private CategoryDAO categoryDao;
	@Autowired private CategoryChecker categoryChecker;

	/**
	 * 신규 카테고리 등록
	 * @param newCategory 신규 카테고리
	 * @return {@link StatusCode}
	 */
	public StatusCode addNewCategory(Category newCategory) {
		StatusCode result = categoryChecker.isValid(newCategory);
		if(result.isSuccess())
			categoryDao.insertNewCategory(newCategory);
		return result;
	}
	
	public Map<String, Category> getCategoryMap() {
		return categoryDao.getCategoryMap();
	}
}
