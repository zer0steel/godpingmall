package com.jyc.godpingmall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.GoodsDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.vo.Goods;

@Service
public class GoodsService {
	
	@Autowired private GoodsDAO goodsDao;
	@Autowired private GoodsOptionService goodsOptionService;

	/**
	 * 신규 상품 등록
	 * @param newGoods
	 * @return
	 */
	public StatusCode addNewGoods(Goods newGoods) {
		StatusCode result = validationCheck(newGoods);
		if(result.isSuccess()) {
			goodsDao.insertNewGoods(newGoods);
			result = goodsOptionService.addNewGoodsOption(newGoods.getGoodsOptionList());
		}
		return result;
	}
	
	public StatusCode validationCheck(Goods newGoods) {
		StatusCode result = newGoods.checkValue();
		if(result.isSuccess()) {
			result = isOverlapGoodsName(newGoods.getName());
		}
		return result;
	}

	private StatusCode isOverlapGoodsName(String name) {
		if(goodsDao.getGoodsMap().containsKey(name))
			return StatusCode.OVERLAP_VALUE.setExtraMessage(name);
		return StatusCode.SUCCESS;
	}

	public Map<String, Goods> getGoodsMap() {
		return goodsDao.getGoodsMap();
	}

}
