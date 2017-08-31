package com.jyc.godpingmall.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.mapper.CategoryMapper;
import com.jyc.godpingmall.vo.Category;

//@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired private CategoryMapper categoryMapper;

	@Override
	public void insertNewCategory(Category newCategory) {
		categoryMapper.insertCategory(newCategory);
	}

	@Override
	public Map<String, Category> getCategoryMap() {
		return categoryMapper.selectAll();
	}
}
