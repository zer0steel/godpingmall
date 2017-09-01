package com.jyc.godpingmall.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.jyc.godpingmall.status.enums.StatusCode;

public class Goods extends BasicGoods {

	private Category category;
	private List<GoodsOption> goodsOptionList;
	
	public Goods() {}
	public Goods(String name, BigDecimal sellPrice, Category category) {
		super();
		this.name = name;
		this.price = sellPrice;
		this.category = category;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<GoodsOption> getGoodsOptionList() {
		return goodsOptionList;
	}
	public void setGoodsOptionList(List<GoodsOption> goodsOptionList) {
		this.goodsOptionList = goodsOptionList;
	}
	
	public BigDecimal getSellPrice() {
		double rate = (100 - this.discountRate) / 100;
		return price.multiply(BigDecimal.valueOf(rate)).setScale(0, BigDecimal.ROUND_DOWN);
	}
	
	@Override
	public StatusCode checkValue() {
		if(ValidationChecker.isEmpty(name))
			return StatusCode.EMPTY_VALUE.setExtraMessage("상품이름");
		if(Objects.isNull(price))
			return StatusCode.EMPTY_VALUE.setExtraMessage("판매가격");
		if(Objects.isNull(category) || ValidationChecker.isEmpty(category.getName()))
			return StatusCode.EMPTY_VALUE.setExtraMessage("카테고리");
		return StatusCode.SUCCESS;
	}
	
}
