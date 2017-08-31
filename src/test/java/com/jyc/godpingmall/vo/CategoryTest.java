package com.jyc.godpingmall.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jyc.godpingmall.enums.CategoryCode;
import com.jyc.godpingmall.enums.StatusCode;

public class CategoryTest {
	
	@Test
	public void check_category_should_be_success() {
		Category category = new Category();
		category.setName("최상위카테고리");
		
		StatusCode result = category.checkValue();
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_category_should_be_fail_when_categoryName_is_null_or_empty() {
		Category nameNullCategory = new Category();
		Category nameEmptyCategory = new Category();
		nameEmptyCategory.setName("");
		
		StatusCode result1 = nameNullCategory.checkValue();
		StatusCode result2 = nameEmptyCategory.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result1);
		assertEquals(StatusCode.EMPTY_VALUE, result2);
	}
	
	@Test
	public void check_topCategory_should_be_fail_when_superCategory_is_exist() {
		Category topCategory = new Category();
		topCategory.setName("최상위카테고리");
		topCategory.setSuperCategory("부모카테고리");
		
		StatusCode result = topCategory.checkValue();
		
		assertEquals(CategoryCode.TOP_CATEGORY_BUT_HAVING_SUPER_CAEGORY.getCode(), result.getCode());
	}
	
	@Test
	public void check_subCategory_should_be_fail_when_superCategory_is_not_exist() {
		Category subCategory = new Category();
		subCategory.setName("자식카테고리");
		subCategory.setLevel(1);
		
		StatusCode result = subCategory.checkValue();
		
		assertEquals(CategoryCode.SUB_CATEGORY_BUT_NOT_HAVING_SUPER_CAEGORY.getCode(), result.getCode());
	}
}
