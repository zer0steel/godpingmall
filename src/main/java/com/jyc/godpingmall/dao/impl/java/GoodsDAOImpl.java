package com.jyc.godpingmall.dao.impl.java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.dao.GoodsDAO;
import com.jyc.godpingmall.vo.Category;
import com.jyc.godpingmall.vo.Goods;

@Repository
class GoodsDAOImpl implements GoodsDAO {
	
	static final Map<String, Goods> map = new HashMap<>();

	@Override
	public Map<String, Goods> getGoodsMap() {
		map.forEach((key, goods) -> {
			Category category = CategoryDAOImpl.map.get(goods.getCategory().getName());
			goods.setCategory(category);
		});
		return map;
	}

	@Override
	public void insertNewGoods(Goods goods) {
		map.put(goods.getName(), goods);
	}
}
