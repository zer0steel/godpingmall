package com.jyc.godpingmall.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.GoodsOption;
import com.jyc.godpingmall.vo.Stock;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {
	
	@InjectMocks private StockService stockService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void insert_new_stock_should_be_success_when_ValidationChecker_is_pass() {
		List<GoodsOption> goodsOptionList = null;
		
		StatusCode result = stockService.insertNewStock(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void insert_new_stock_should_be_success_when_ValidationChecker_is_not_pass() {
		List<GoodsOption> goodsOptionList = VOProvider.getGoodsOptionList();
		
		StatusCode result = stockService.insertNewStock(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
}
