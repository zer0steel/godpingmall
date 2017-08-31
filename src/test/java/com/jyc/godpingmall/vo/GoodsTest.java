package com.jyc.godpingmall.vo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.testutil.VOProvider;

public class GoodsTest {

	@Test
	public void check_should_be_success() {
		Goods g = new Goods("상품명", BigDecimal.ZERO, VOProvider.getCategory());
		
		StatusCode result = g.checkValue();
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_should_be_fail_when_goodsName_is_empty_or_null() {
		String nullName = null, emptyName = "";
		
		Goods goodsNameNull = new Goods(nullName, BigDecimal.ZERO, new Category("카테고리"));
		Goods goodsNameEmpty = new Goods(emptyName, BigDecimal.ZERO, new Category("카테고리"));
		
		StatusCode result1 = goodsNameNull.checkValue();
		StatusCode result2 = goodsNameEmpty.checkValue();
		
		String message = StatusCode.EMPTY_VALUE.getMessage().replaceAll("%", "상품이름");
		assertEquals(message, result1.getMessage());
		assertEquals(message, result2.getMessage());
	}
	
	@Test
	public void check_should_be_fail_when_sellPrice_is_null() {
		BigDecimal nullPrice = null;
		
		Goods goodPriceNull = new Goods("상품명", nullPrice, new Category("카테고리"));
		
		StatusCode result = goodPriceNull.checkValue();
		
		String message = StatusCode.EMPTY_VALUE.getMessage().replaceAll("%", "판매가격");
		assertEquals(message, result.getMessage());
	}
	
	@Test
	public void check_should_be_fail_when_category_is_null() {
		Category nullCategory = null;
		
		Goods goodPriceNull = new Goods("상품명", BigDecimal.ZERO, nullCategory);
		
		StatusCode result = goodPriceNull.checkValue();
		
		String message = StatusCode.EMPTY_VALUE.getMessage().replaceAll("%", "카테고리");
		assertEquals(message, result.getMessage());
	}
	
	@Test
	public void sellPrice_should_equals_price_when_discountRate_is_zero() {
		Goods goods = new Goods();
		goods.setPrice(BigDecimal.valueOf(1000));
		
		BigDecimal result = goods.getSellPrice();
		
		assertEquals(goods.getPrice(), result);
	}
	
	@Test
	public void sellPrice_should_be_900_when_discountRate_is_10_and_price_is_1000() {
		Goods goods = new Goods();
		goods.setPrice(BigDecimal.valueOf(1000));
		goods.setDiscountRate("10");
		
		BigDecimal result = goods.getSellPrice();
		
		assertEquals(BigDecimal.valueOf(900), result);
	}
}
