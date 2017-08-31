package com.jyc.godpingmall.dao;

import java.util.Map;

import com.jyc.godpingmall.vo.Category;

public interface CategoryDAO {

	void insertNewCategory(Category newCategory);
	
	Map<String, Category> getCategoryMap();
}
