package com.jyc.godpingmall.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.jyc.godpingmall.status.enums.StatusCode;

public abstract class BasicGoods implements ValidationChecker {

	private Long id;
	protected String name;
	protected BigDecimal price;
	protected double discountRate = 0;
	private Timestamp regDate;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = ValidationChecker.isEmpty(discountRate) ? 
				0 : Double.valueOf(discountRate);
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
