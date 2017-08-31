package com.jyc.godpingmall.service.checker;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.CategoryDAO;
import com.jyc.godpingmall.enums.CategoryCode;
import com.jyc.godpingmall.enums.StatusCode;
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
		categoryMap = categoryDao.getCategoryMap();
		if(categoryMap.containsKey(category.getName()))
			return StatusCode.OVERLAP_VALUE.setExtraMessage(category.getName());
		if(!category.isTopCategory() && !isExistCategoryName(category))
			return StatusCode.getFailStatus(CategoryCode.NONE_SUPER_CATEGORY);
		return StatusCode.SUCCESS;
	}

	/**
	 * 상위 카테고리에 이미 존재하는 이름인지 확인
	 * @param name 카테고리 이름
	 * @return <p>true : 중복 카테고리 이름
	 */
	private boolean isExistCategoryName(Category subCategory) {
		Category superCategory = categoryMap.get(subCategory.getSuperCategory());
		if(Objects.nonNull(superCategory) && isSubLevel(subCategory, superCategory))
			return true;
		return false;
	}

	/**
	 * 직속 하위 카테고리 확인
	 * @param subCategory 하위 카테고리
	 * @param superCategory 상위 카테고리
	 * @return <p>true : 직속 하위 카테고리
	 */
	private boolean isSubLevel(Category subCategory, Category superCategory) {
		return superCategory.getLevel() + 1 == subCategory.getLevel();
	}

}
