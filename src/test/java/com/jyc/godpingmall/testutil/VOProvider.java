package com.jyc.godpingmall.testutil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyc.godpingmall.vo.Category;
import com.jyc.godpingmall.vo.Goods;
import com.jyc.godpingmall.vo.GoodsOption;

public class VOProvider {
	
	public static Map<String, Category> getCategoryMap() {
		Category category = new Category("중복카테고리");
		Category category1 = new Category("상위카테고리");
		
		Map<String, Category> map = new HashMap<>();
		map.put(category.getName(), category);
		map.put(category1.getName(), category1);
		return map;
	}
	
	public static Map<String, Goods> getGoodsMap() {
		Goods g = new Goods("중복상품이름", BigDecimal.ZERO, new Category("카테고리"));
		
		Map<String, Goods> map = new HashMap<>();
		map.put(g.getName(), g);
		return map;
	}
	
	public static List<GoodsOption> getGoodsOptionList() {
		GoodsOption option = new GoodsOption(Long.valueOf(1), "옵션", "옵션값");
		List<GoodsOption> list = new ArrayList<>();
		list.add(option);
		return list;
	}
	
	public static Category getCategory() {
		Category category = new Category("카테고리");
		category.setId(Long.valueOf(1));
		return category;
	}
}
