package com.jyc.godpingmall.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jyc.godpingmall.enums.StatusCode;

public class GoodsOptionTest {
	
	private GoodsOption option;

	@Before
	public void setup() {
		option = new GoodsOption(Long.valueOf(1), "옵션", "옵션값");
	}
	
	@Test
	public void check_goodsOption_should_be_success() {
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_goodsOption_should_be_fail_when_goodsOptionName_is_empty() {
		String emptyName = "";
		option.setOptionName(emptyName);
		
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_goodsOption_should_be_fail_when_goodsOptionName_is_null() {
		String nullName = null;
		option.setOptionName(nullName);
		
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_goodsOption_should_be_fail_when_goodsOptionValue_is_empty() {
		String emptyValue = "";
		option.setOptionValue(emptyValue);
		
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_goodsOption_should_be_fail_when_goodsOptionValue_is_null() {
		String nullValue = null;
		option.setOptionValue(nullValue);
		
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_goodsOption_should_be_fail_when_goodsId_is_null() {
		Long nullGoodsId = null;
		option.setGoodsId(nullGoodsId);
		
		StatusCode result = option.checkValue();
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
}
