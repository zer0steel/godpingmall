package com.jyc.godpingmall.vo;

import java.util.Objects;
import java.util.Optional;

import com.jyc.godpingmall.status.enums.CategoryCode;
import com.jyc.godpingmall.status.enums.StatusCode;

public class Category implements ValidationChecker {

	private Long id;
	private String name;
	private String superName;
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
	public String getSuperName() {
		return superName;
	}
	public void setSuperName(String superName) {
		this.superName = superName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(String level) {
		if(ValidationChecker.isEmpty(level))
			this.level = 0;
		this.level = Integer.parseInt(level);
	}
	
	@Override
	public StatusCode checkValue() {
		if(ValidationChecker.isEmpty(name))
			return StatusCode.EMPTY_VALUE.setExtraMessage("카테고리 이름");
		if(isTopCategoryAndExistSuperCategory())
			return StatusCode.getFailStatus(CategoryCode.TOP_CATEGORY_BUT_HAVING_SUPER_CAEGORY);
		if(isSubCategoryAndNoneSuperCategory())
			return StatusCode.getFailStatus(CategoryCode.SUB_CATEGORY_BUT_NOT_HAVING_SUPER_CAEGORY);
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
	 * 하위 카테고리 확인
	 * @return <p>true : 하위 카테고리</p><p>false : 최상위 카테고리</p>
	 */
	public boolean isSubCategory() {
		return this.level != 0;
	}
	
	/**
	 * 상위 카테고리 가지고 있는지 확인
	 * @return <p>true : 상위카테고리 값 존재</p>
	 */
	public boolean isExistSuperCategory() {
		return Objects.isNull(superName) ? false : !superName.isEmpty();
	}
	
	/**
	 * 상위 카테고리인지 확인한다.
	 * @param superCategory 확인 대상 객체
	 * @return <p> {@link StatusCode#SUCCESS} : 상위카테고리<p> {@link StatusCode#NONE_VALUE} : 상위카테고리 아님
	 */
	public StatusCode checkSuperCategory(Category superCategory) {
		if(Objects.isNull(superCategory))
			return StatusCode.NONE_VALUE.setExtraMessage("상위카테고리");
		if(this.superName.equalsIgnoreCase(superCategory.getName()) && checkSuperLevel(superCategory))
			return StatusCode.SUCCESS;
		return StatusCode.NONE_VALUE.setExtraMessage("상위카테고리");
	}
	private boolean checkSuperLevel(Category superCategory) {
		return this.level == superCategory.level + 1;
	}
}
