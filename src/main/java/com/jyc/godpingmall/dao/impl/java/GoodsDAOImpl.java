package com.jyc.godpingmall.dao.impl.java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.dao.GoodsDAO;
import com.jyc.godpingmall.vo.Goods;

@Repository
class GoodsDAOImpl implements GoodsDAO {
	
	private static final Map<String, Goods> map = new HashMap<>();

	@Override
	public Map<String, Goods> getGoodsMap() {
		return map;
	}

	@Override
	public void insertNewGoods(Goods goods) {
		map.put(goods.getName(), goods);
	}
}
