package com.jyc.godpingmall.service.checker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.status.enums.CategoryCode;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.Category;

/**
 * <p>카테고리 유효성 체크 테스트
 * <ul>
 * <li>최상위 카테고리 체크 성공
 * <li>서브 카테고리 체크 성공
 * <li>카테고리 이름값이 존재하지 않아서 체크 실패
 * <li>카테고리 이름값이 기존과 중복되어 체크 실패
 * <li>최상위 카테고리가 상위 카테고리를 가져서 체크 실패
 * <li>서브 카테고리가 상위 카테고리가 없어서 체크 실패
 * <li>찾으려는 상위카테고리가 없어서 체크 실패
 * @author Jang Young Cheol
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryCheckerTest {
	
	@Mock private CategoryDAO categoryDao = mock(CategoryDAO.class);
	@InjectMocks private CategoryChecker checker;

	@Before
	public void setUp() throws Exception {
		when(categoryDao.getCategoryMap()).thenReturn(VOProvider.getCategoryMap());
	}
	
	@Test
	public void check_topCategory_should_be_success() {
		Category category = new Category();
		category.setName("성공 최상위카테고리");
		
		StatusCode result = checker.isValid(category);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_subCategory_should_be_success() {
		Category subCategory = new Category();
		subCategory.setName("성공 하위카테고리");
		subCategory.setLevel("1");
		subCategory.setSuperName("상위카테고리");
		
		StatusCode result = checker.isValid(subCategory);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_category_should_be_fail_when_categoryName_is_overlap() {
		Category category = new Category("중복카테고리");
		
		StatusCode result = checker.isValid(category);
		
		assertEquals(StatusCode.OVERLAP_VALUE, result);
	}
	
	@Test
	public void check_subCategory_should_be_fail_when_superCategory_is_not_found() {
		Category newCategory = new Category();
		newCategory.setName("하위카테고리");
		newCategory.setLevel("1");
		newCategory.setSuperName("없는 상위카테고리");
		
		Category newCategory2 = new Category();
		newCategory2.setName("하위카테고리");
		newCategory2.setLevel("2");
		newCategory2.setSuperName("상위카테고리");
		
		StatusCode result = checker.isValid(newCategory);
		StatusCode result2 = checker.isValid(newCategory2);
		
		assertEquals(StatusCode.NONE_VALUE, result);
		assertEquals(StatusCode.NONE_VALUE, result2);
	}
	
}
