package com.jyc.godpingmall.vo;

import java.math.BigDecimal;

public class Stock {
	
	private Long id;
	private Long goodsId;
	private String name;
	private int amount;
	private BigDecimal extraPrice = BigDecimal.ZERO;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getExtraPrice() {
		return extraPrice;
	}
	public void setExtraPrice(BigDecimal extraPrice) {
		this.extraPrice = extraPrice;
	}
	
	public void setStock(GoodsOption[] goodsOption) {
		StringBuilder name = new StringBuilder();
		for (int i = goodsOption.length - 1; i >= 0; i--) {
			GoodsOption opt = goodsOption[i];
			name.append(opt.getOptionValue()).append("/");
			extraPrice.add(opt.getExtraPrice());
		}
		this.name = name.delete(name.lastIndexOf("/"), name.length()).toString();
	}
	
}
