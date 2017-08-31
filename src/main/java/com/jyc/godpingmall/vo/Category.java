package com.jyc.godpingmall.vo;

import java.util.Optional;

import com.jyc.godpingmall.enums.CategoryCode;
import com.jyc.godpingmall.enums.StatusCode;

public class Category implements ValidationChecker {

	private Long id;
	private String name;
	private String superCategory;
	private int level;
	
	public Category() {}
	public Category(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuperCategory() {
		return superCategory;
	}
	public void setSuperCategory(String superCategory) {
		this.superCategory = superCategory;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public StatusCode checkValue() {
		if(ValidationChecker.isEmpty(name))
			return StatusCode.EMPTY_VALUE.setExtraMessage("카테고리 이름");
		if(isTopCategoryAndExistSuperCategory())
			return StatusCode.getStatus(CategoryCode.TOP_CATEGORY_BUT_HAVING_SUPER_CAEGORY);
		if(isSubCategoryAndNoneSuperCategory())
			return StatusCode.getStatus(CategoryCode.SUB_CATEGORY_BUT_NOT_HAVING_SUPER_CAEGORY);
		return StatusCode.SUCCESS;
	}
	
	/**
	 * 최상위 카테고리(level == 0) 이면서 상위 카테고리를 보유중인지 확인
	 * @return <p>true : 최상위 카테고리 이면서 상위 카테고리 존재</p>
	 */
	private boolean isTopCategoryAndExistSuperCategory() {
		return isTopCategory() && isExistSuperCategory();
	}
	
	/**
	 * 하위 카테고리이면서 상위 카테고리가 없는지 확인
	 * @return <p>true : 하위 카테고리인데 상위 카테고리 없음</p>
	 */
	private boolean isSubCategoryAndNoneSuperCategory() {
		return !isTopCategory() && !isExistSuperCategory();
	}
	
	/**
	 * 최상위 카테고리 확인
	 * @return <p>true : 최상위 카테고리</p><p>false : 하위 카테고리</p>
	 */
	public boolean isTopCategory() {
		return this.level == 0;
	}
	
	/**
	 * 상위 카테고리 가지고 있는지 확인
	 * @return <p>true : 상위카테고리 값 존재</p>
	 */
	public boolean isExistSuperCategory() {
		return Optional.ofNullable(this.superCategory)
				.filter(category -> !category.isEmpty())
				.isPresent();
	}
}
