package com.jyc.godpingmall.status.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jyc.godpingmall.status.Status;

/**
 * <p>가장 기본적인 반환코드
 * <ul>
 * <li>성공 : {@link StatusCode#SUCCESS}
 * <li>중복 : {@link StatusCode#OVERLAP_VALUE}
 * <li>입력된 값이 존재하지 않음 : {@link StatusCode#EMPTY_VALUE}
 * <li>값이 기존 데이터에서 존재하지 않음 : {@link StatusCode#NONE_VALUE}
 * <li>기타 실패 : {@link StatusCode#FAIL}
 */
@JsonFormat(shape = Shape.OBJECT)
public enum StatusCode implements Status {
	SUCCESS(200, "성공"), 
	/**
	 * <p>반환 메시지 : %s은(는) 이미 존재하는 값입니다.
	 * <p>setExtraMessage 메소드를 사용하여 %s에 문자 넣어야함.
	 */
	OVERLAP_VALUE(400, "%s은(는) 이미 존재합니다."),
	/**
	 * <p>반환 메시지 : %s이(가) 입력되지 않았습니다.
	 * <p>setExtraMessage 메소드를 사용하여 %s에 문자 넣어야함.
	 */
	EMPTY_VALUE(401, "%s이(가) 입력되지 않았습니다."),
	/**
	 * <p>반환 메시지 : %s이(가) 존재하지 않습니다.
	 * <p>setExtraMessage 메소드를 사용하여 %s에 문자 넣어야함.
	 */
	NONE_VALUE(402, "%s이(가) 존재하지 않습니다."),
	/**
	 * <p>{@link Status} 를 확장한 반환코드의 데이터를 사용하기 위하여 존재.
	 */
	FAIL(450, "요청을 처라하는 도중 문제가 발생했습니다. 관리자에게 문의해 주세요.");
	
	private StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String message, customMessage;
	private int code;
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return Objects.isNull(customMessage) ? message : customMessage;
	}

	/**
	 * 추가 문자가 필요한 메시지에 문자 세팅
	 * @param str 추가 문자
	 * @return {@link StatusCode} this
	 */
	public StatusCode setExtraMessage(String str) {
		this.customMessage = this.message.replace("%s", str);
		return this;
	}
	
	/**
	 * 반환 코드와 문자를 세팅
	 * @param status 세팅할 데이터가 있는 객체
	 */
	private void setStatus(Status status) {
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	/**
	 * <p>확장 반환코드 객체를 기본반환코드 객체로 변환.
	 * <p>{@link StatusCode#FAIL} 로 할당된다.
	 * @param status
	 * @return {@link StatusCode}
	 */
	public static StatusCode getFailStatus(Status status) {
		StatusCode fail = StatusCode.FAIL;
		fail.setStatus(status);
		return fail;
	}

	/**
	 * @return <p>true : 결과가 성공
	 */
	public boolean isSuccess() {
		return this == StatusCode.SUCCESS;
	}
	
	/**
	 * @return <p>true : 결과가 성공이 아님
	 */
	public boolean isNotSuccess() {
		return this != StatusCode.SUCCESS;
	}
}
