package com.jyc.godpingmall.vo;

import java.math.BigDecimal;
import java.util.Objects;

import com.jyc.godpingmall.enums.StatusCode;

public class GoodsOption implements ValidationChecker {

	private Long id, goodsId;
	private String optionName;
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
}
