package com.jyc.godpingmall.service.checker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.OptionDAO;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.GoodsOption;

@RunWith(MockitoJUnitRunner.class)
public class OptionCheckerTest {
	
	@Inject private OptionDAO optionDao = mock(OptionDAO.class);
	@InjectMocks private OptionChecker optionChecker;
	
	@Test
	public void check_option_should_be_success() {
		String optionName = "옵션";
		
		StatusCode result = optionChecker.loadOptionAndValidationCheck(optionName);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_option_should_be_fail_when_option_is_null() {
		String optionNameNull = null;
		
		StatusCode result = optionChecker.loadOptionAndValidationCheck(optionNameNull);
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_option_should_be_fail_when_option_is_empty() {
		String optionNameEmpty = "";
		
		StatusCode result = optionChecker.loadOptionAndValidationCheck(optionNameEmpty);
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_option_should_be_fail_when_option_is_overlap() {
		String optionName = "중복옵션";
		
		when(optionDao.getOptionList()).thenReturn(Arrays.asList(optionName));
		
		StatusCode result = optionChecker.loadOptionAndValidationCheck(optionName);
		
		assertEquals(StatusCode.OVERLAP_VALUE, result);
	}
	
	@Test
	public void check_goodsOptionList_should_be_success() {
		List<GoodsOption> goodsOptionList = VOProvider.getGoodsOptionList();
		
		when(optionDao.getOptionList()).thenReturn(Arrays.asList("옵션"));
		
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_goodsOptionList_should_be_success_when_goodsOptionList_is_null() {
		List<GoodsOption> goodsOptionList = null;
		
		when(optionDao.getOptionList()).thenReturn(Arrays.asList("옵션"));
		
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_goodsOptionList_should_be_success_when_goodsOptionList_is_empty() {
		List<GoodsOption> goodsOptionList = new ArrayList<>();
		
		when(optionDao.getOptionList()).thenReturn(Arrays.asList("옵션"));
		
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void check_goodsOptionList_should_be_fail_when_goodsOption_checkValue_is_fail() {
		List<GoodsOption> goodsOptionList = VOProvider.getGoodsOptionList();
		goodsOptionList.get(0).setOptionName(null);
		
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		
		assertEquals(StatusCode.EMPTY_VALUE, result);
	}
	
	@Test
	public void check_goodsOptionList_should_be_fail_when_optionName_is_not_found() {
		List<GoodsOption> goodsOptionList = VOProvider.getGoodsOptionList();
		
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		
		assertEquals(StatusCode.NONE_VALUE, result);
	}
}
