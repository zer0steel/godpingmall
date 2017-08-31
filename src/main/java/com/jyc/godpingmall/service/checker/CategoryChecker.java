package com.jyc.godpingmall.service.checker;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.status.enums.CategoryCode;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.vo.Category;

@Service
public class CategoryChecker {
	
	@Autowired private CategoryDAO categoryDao;
	private Map<String, Category> categoryMap;

	/**
	 * 유효한 카테고리인지 확인
	 * @param category
	 * @return {@link StatusCode}
	 */
	public StatusCode isValid(Category category) {
		StatusCode result = category.checkValue();
		if(result.isSuccess())
			result = checkExistValue(category);
		return result;
	}

	/**
	 * 등록되어 있는 카테고리들과 비교
	 * @param category
	 * @return {@link StatusCode}
	 */
	private StatusCode checkExistValue(Category category) {
		if(loadCategoryAndcheckExistCategoryName(category))
			return StatusCode.OVERLAP_VALUE.setExtraMessage(category.getName());
		if(category.isSubCategory())
			return category.checkSuperCategory(getSuperCategory(category));
		return StatusCode.SUCCESS;
	}

	/**
	 * 이미 존재하는 카테고리이름인지 확인
	 * @param categoryName
	 * @return <p>true : 존재하는 카테고리 이름
	 */
	public boolean loadCategoryAndcheckExistCategoryName(Category category) {
		categoryMap = categoryDao.getCategoryMap();
		return categoryMap.containsKey(category.getName());
	}

	/**
	 * 상위 카테고리를 가져온다.
	 * @param subCategory
	 * @return
	 */
	private Category getSuperCategory(Category subCategory) {
		return categoryMap.get(subCategory.getSuperName());
	}
}
