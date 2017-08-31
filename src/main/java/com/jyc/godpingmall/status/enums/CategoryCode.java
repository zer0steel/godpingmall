package com.jyc.godpingmall.status.enums;

import com.jyc.godpingmall.status.Status;

public enum CategoryCode implements Status {
	TOP_CATEGORY_BUT_HAVING_SUPER_CAEGORY(402, "최상위 카테고리는 상위 카테고리를 가질 수 없습니다."), 
	SUB_CATEGORY_BUT_NOT_HAVING_SUPER_CAEGORY(403, "상위 카테고리가 존재하지 않습니다.");
	
	private CategoryCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String message;
	private int code;
	
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		return message;
	}
	
}
