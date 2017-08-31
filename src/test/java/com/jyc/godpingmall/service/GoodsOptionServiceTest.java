package com.jyc.godpingmall.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.GoodsOptionDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.OptionChecker;
import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.GoodsOption;

@RunWith(MockitoJUnitRunner.class)
public class GoodsOptionServiceTest {
	
	@Mock private GoodsOptionDAO goodsOptionDao = mock(GoodsOptionDAO.class);
	@Mock private OptionChecker optionChecker = mock(OptionChecker.class);
	@InjectMocks private GoodsOptionService goodsOptionService;
	private List<GoodsOption> goodsOptionList = new ArrayList<>();
	
	@Before
	public void setup() {
		goodsOptionList.clear();
	}
	
	@Test
	public void insert_goodsOption_should_be_success() {
		goodsOptionList = VOProvider.getGoodsOptionList();
		
		when(optionChecker.checkGoodsOptionList(goodsOptionList)).thenReturn(StatusCode.SUCCESS);
		
		StatusCode result = goodsOptionService.addNewGoodsOption(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void insert_goodsOption_should_be_fail_when_check_is_not_success() {
		goodsOptionList = VOProvider.getGoodsOptionList();
		
		when(optionChecker.checkGoodsOptionList(goodsOptionList)).thenReturn(StatusCode.NONE_VALUE);
		
		StatusCode result = goodsOptionService.addNewGoodsOption(goodsOptionList);
		
		assertNotEquals(StatusCode.SUCCESS, result);
	}
	
}
