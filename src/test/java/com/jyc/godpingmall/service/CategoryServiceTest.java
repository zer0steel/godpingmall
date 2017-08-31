package com.jyc.godpingmall.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.enums.CategoryCode;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.CategoryChecker;
import com.jyc.godpingmall.vo.Category;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	
	@Mock private CategoryChecker categoryChecker = mock(CategoryChecker.class);
	@Mock private CategoryDAO categoryDao = mock(CategoryDAO.class);
	@InjectMocks CategoryService categoryService;
	
	@Test
	public void adding_newCategory_should_be_success() {
		Category newCategory = new Category();
		
		when(categoryChecker.isValid(newCategory)).thenReturn(StatusCode.SUCCESS);
		
		StatusCode result = categoryService.addNewCategory(newCategory);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void adding_newCategory_should_be_fail_when_check_is_not_pass() {
		Category newCategory = new Category();
		
		when(categoryChecker.isValid(newCategory)).thenReturn(StatusCode.EMPTY_VALUE);
		
		StatusCode result = categoryService.addNewCategory(newCategory);
		
		assertNotEquals(StatusCode.SUCCESS, result);
	}
}
