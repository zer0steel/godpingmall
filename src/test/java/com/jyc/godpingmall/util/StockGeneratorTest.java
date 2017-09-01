package com.jyc.godpingmall.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.GoodsOption;
import com.jyc.godpingmall.vo.Stock;

public class StockGeneratorTest {

	private Long goodsId = Long.valueOf(1);
	
	@Test
	public void creation_stock_list_should_return_list_with_size_one_when_goodsOptionList_size_is_zero() {
		List<GoodsOption> goodsOptionList = null;
		
		List<Stock> result = new StockGenerator(goodsOptionList, goodsId).getResult();
		
		assertEquals(1, result.size());
		assertEquals("기본옵션", result.get(0).getName());
	}
	
	@Test
	public void creation_stock_list_should_return_list_with_size_one_when_goodsOptionList_size_is_one() {
		List<GoodsOption> goodsOptionList = VOProvider.getGoodsOptionList();
		
		List<Stock> result = new StockGenerator(goodsOptionList, goodsId).getResult();
		
		assertEquals(1, result.size());
		assertEquals(goodsOptionList.get(0).getOptionValue(), result.get(0).getName());
	}
	
	@Test
	public void creation_stock_list_should_return_list_with_size_N_when_count_of_option_name_is_one_and_count_of_option_value_is_N() {
		List<GoodsOption> goodsOptionList = new ArrayList<>();
		goodsOptionList.add(new GoodsOption(Long.valueOf(1), "옵션", "1"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(2), "옵션", "2"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(3), "옵션", "3"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(4), "옵션", "4"));
		
		List<Stock> result = new StockGenerator(goodsOptionList, goodsId).getResult();
		
		assertEquals(4, result.size());
		for (int i = 0; i < result.size(); i++)
			assertEquals((i + 1) + "", result.get(i).getName());
	}
	
	@Test
	public void creation_stock_list_should_return_list_with_size_multiply_all_count_of_option_value_par_option() {
		List<GoodsOption> goodsOptionList = new ArrayList<>();
		goodsOptionList.add(new GoodsOption(Long.valueOf(1), "옵션", "1"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(2), "옵션", "2"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(2), "옵션", "3"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(3), "옵션2", "10"));
		goodsOptionList.add(new GoodsOption(Long.valueOf(4), "옵션2", "20"));
		
		List<Stock> result = new StockGenerator(goodsOptionList, goodsId).getResult();	
		List<String> resultList = Arrays.asList(result.stream().map(s -> s.getName()).toArray(String[]::new));
		List<String> expectResultString = Arrays.asList("1/10", "1/20", "2/10", "2/20", "3/10", "3/20");
		
		assertEquals(6, result.size());
		resultList.forEach(name -> {
			assertTrue(expectResultString.contains(name));
		});
	}
}
