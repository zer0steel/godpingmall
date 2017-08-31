package com.jyc.godpingmall.dao.impl.java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.vo.Category;

@Repository
class CategoryDAOImpl implements CategoryDAO {
	
	static final Map<String, Category> map = new HashMap<>();

	@Override
	public void insertNewCategory(Category newCategory) {
		map.put(newCategory.getName(), newCategory);
	}

	@Override
	public Map<String, Category> getCategoryMap() {
		return map;
	}
}
