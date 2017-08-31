package com.jyc.godpingmall.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.GoodsDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.testutil.VOProvider;
import com.jyc.godpingmall.vo.Category;
import com.jyc.godpingmall.vo.Goods;

@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceTest {

	@Mock private GoodsDAO goodsDao = mock(GoodsDAO.class);
	@Mock private GoodsOptionService goodsOptionService = mock(GoodsOptionService.class);
	@InjectMocks private GoodsService goodsService;
	
	@Test
	public void insert_newGoods_should_be_success() {
		Goods g = new Goods("상품이름", BigDecimal.ZERO, VOProvider.getCategory());
		
		when(goodsOptionService.addNewGoodsOption(g.getGoodsOptionList())).thenReturn(StatusCode.SUCCESS);
		StatusCode result = goodsService.addNewGoods(g);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void insert_newGoods_should_be_fail_when_insert_goodsOption_is_fail() {
		Goods g = new Goods("상품이름", BigDecimal.ZERO, new Category("카테고리"));
		
		when(goodsOptionService.addNewGoodsOption(g.getGoodsOptionList())).thenReturn(StatusCode.NONE_VALUE);
		StatusCode result = goodsService.addNewGoods(g);
		
		assertNotEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void insert_newGoods_should_be_fail_when_goods_name_is_overlap() {
		String overlapName = "중복상품이름";
		
		Goods g = new Goods(overlapName, BigDecimal.ZERO, VOProvider.getCategory());
		
		when(goodsDao.getGoodsMap()).thenReturn(VOProvider.getGoodsMap());
		
		StatusCode result = goodsService.addNewGoods(g);
		
		assertEquals(StatusCode.OVERLAP_VALUE, result);
	}
}
