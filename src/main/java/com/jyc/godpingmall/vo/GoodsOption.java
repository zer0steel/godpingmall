package com.jyc.godpingmall.vo;

import java.math.BigDecimal;
import java.util.Objects;

import com.jyc.godpingmall.status.enums.StatusCode;

public class GoodsOption implements ValidationChecker {

	private Long id, goodsId;
	/**
	 * <p>옵션 이름
	 * <p>ex) 색상 (옵션이름) : 빨간색, 파란색, 보라색 (옵션 값)
	 */
	private String optionName;
	/**
	 * <p>옵션 값
	 * <p>ex) 색상 (옵션이름) : 빨간색, 파란색, 보라색 (옵션 값)
	 */
	private String optionValue;
	private BigDecimal extraPrice = BigDecimal.ZERO;
	public GoodsOption() {}
	/**
	 * 필수값 생성자
	 * @param goodsId
	 * @param optionName
	 * @param optionValue
	 */
	public GoodsOption(Long goodsId, String optionName, String optionValue) {
		super();
		this.goodsId = goodsId;
		this.optionName = optionName;
		this.optionValue = optionValue;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 옵션 이름 반환
	 * @return {@link #optionName}
	 */
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public BigDecimal getExtraPrice() {
		return extraPrice;
	}
	public void setExtraPrice(BigDecimal extraPrice) {
		this.extraPrice = extraPrice;
	}
	/**
	 * 옵션 값 반환
	 * @return {@link #optionValue}
	 */
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	
	@Override
	public StatusCode checkValue() {
		if(Objects.isNull(goodsId))
			return StatusCode.EMPTY_VALUE.setExtraMessage("상품번호");
		if(ValidationChecker.isEmpty(optionName))
			return StatusCode.EMPTY_VALUE.setExtraMessage("옵션");
		if(ValidationChecker.isEmpty(optionValue))
			return StatusCode.EMPTY_VALUE.setExtraMessage("옵션값");
		return StatusCode.SUCCESS;
	}
	@Override
	public String toString() {
		return "GoodsOption [id=" + id + ", goodsId=" + goodsId + ", optionName=" + optionName + ", optionValue="
				+ optionValue + ", extraPrice=" + extraPrice + "]";
	}
	
}
