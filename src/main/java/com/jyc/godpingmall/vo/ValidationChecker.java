package com.jyc.godpingmall.vo;

import java.io.File;
import java.util.List;
import java.util.Objects;

import com.jyc.godpingmall.status.enums.StatusCode;

public interface ValidationChecker {
	
	/**
	 * 문자 조합시 사용하는 구분자
	 */
	public String SEPARATOR = " -|- ";
	
	/**
	 * 자체적인 유효성 검사
	 * @return {@link StatusCode}
	 */
	StatusCode checkValue();
	
	/**
	 * Null 포함하여 값이 없는지 확인
	 * @param value 확인하려는 문자
	 * @return <p>true : null 이거나 빈문자열
	 */
	static boolean isEmpty(String value) {
		return Objects.isNull(value) ? true : value.isEmpty();
	}
	
	/**
	 * null 포함하여 리스트가 비었는지 확인
	 * @param list 확인하려는 리스트
	 * @return <p>true : null 이거나 빈리스트
	 */
	static boolean isEmpty(List<?> list) {
		return Objects.isNull(list) ? true : list.isEmpty();
	}
}
