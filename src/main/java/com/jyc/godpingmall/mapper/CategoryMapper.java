package com.jyc.godpingmall.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.vo.Category;

@Repository
public interface CategoryMapper {

	@Insert("INSERT INTO category(name, super_name, level) VALUES(#{name }, #{superCategory }, #{level })")
	void insertCategory(Category newCategory);

	@Select("SELECT * FROM category")
	Map<String, Category> selectAll();

}
