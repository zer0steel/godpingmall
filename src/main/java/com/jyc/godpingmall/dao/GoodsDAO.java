package com.jyc.godpingmall.dao;

import java.util.Map;

import com.jyc.godpingmall.vo.Goods;

public interface GoodsDAO {

	Map<String, Goods> getGoodsMap();
	
	void insertNewGoods(Goods goods);
}
