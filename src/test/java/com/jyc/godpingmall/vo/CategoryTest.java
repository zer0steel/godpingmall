package com.jyc.godpingmall.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jyc.godpingmall.status.enums.CategoryCode;
import com.jyc.godpingmall.status.enums.StatusCode;

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
		topCategory.setSuperName("부모카테고리");
		
		StatusCode result = topCategory.checkValue();
		
		assertEquals(CategoryCode.TOP_CATEGORY_BUT_HAVING_SUPER_CAEGORY.getCode(), result.getCode());
	}
	
	@Test
	public void check_subCategory_should_be_fail_when_superCategory_is_not_exist() {
		Category subCategory = new Category();
		subCategory.setName("자식카테고리");
		subCategory.setLevel("1");
		
		StatusCode result = subCategory.checkValue();
		
		assertEquals(CategoryCode.SUB_CATEGORY_BUT_NOT_HAVING_SUPER_CAEGORY.getCode(), result.getCode());
	}
	
	@Test
	public void check_superCategory_should_be_success() {
		Category superCategory = new Category();
		superCategory.setName("상위카테고리");
		Category subCategory = new Category();
		subCategory.setName("하위카테고리");
		subCategory.setLevel("1");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = subCategory.checkSuperCategory(superCategory);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_superCategory_should_be_fail_when_superCategory_is_null() {
		Category subCategory = new Category();
		subCategory.setName("하위카테고리");
		subCategory.setLevel("1");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = subCategory.checkSuperCategory(null);
		
		assertEquals(StatusCode.NONE_VALUE, result);
	}
	
	@Test
	public void check_superCategory_should_be_fail_when_superCategoryName_is_null() {
		Category superCategory = new Category();
		Category subCategory = new Category();
		subCategory.setName("하위카테고리");
		subCategory.setLevel("1");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = subCategory.checkSuperCategory(superCategory);
		
		assertEquals(StatusCode.NONE_VALUE, result);
	}
	
	@Test
	public void check_superCategory_should_be_fail_when_subName_and_superName_is_not_equals() {
		Category superCategory = new Category();
		superCategory.setName("다른카테고리");
		Category subCategory = new Category();
		subCategory.setName("하위카테고리");
		subCategory.setLevel("1");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = subCategory.checkSuperCategory(superCategory);
		
		assertEquals(StatusCode.NONE_VALUE, result);
	}
	
	@Test
	public void check_superCategory_should_be_fail_when_level_check_is_fail() {
		Category superCategory = new Category();
		superCategory.setName("상위카테고리");
		Category subCategory = new Category();
		subCategory.setName("하위카테고리");
		subCategory.setLevel("2");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = subCategory.checkSuperCategory(superCategory);
		
		assertEquals(StatusCode.NONE_VALUE, result);
	}
}
