package com.jyc.godpingmall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.jyc.godpingmall.dao.GoodsDAO;
import com.jyc.godpingmall.service.checker.CategoryChecker;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.vo.Category;
import com.jyc.godpingmall.vo.Goods;

@Service
public class GoodsService {
	
	@Autowired private GoodsDAO goodsDao;
	@Autowired private CategoryChecker categoryChecker;
	@Autowired private GoodsOptionService goodsOptionService;

	/**
	 * 신규 상품 등록
	 * @param newGoods
	 * @return
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public StatusCode addNewGoods(Goods newGoods) {
		StatusCode result = validationCheck(newGoods);
		if(result.isSuccess()) {
			goodsDao.insertNewGoods(newGoods);
			result = goodsOptionService.addNewGoodsOption(newGoods.getGoodsOptionList());
			if(result.isNotSuccess()) {
			}
		}
//			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		return result;
	}
	
	/**
	 * <p>상품 등록전 유효성 검사
	 * <p>상품명, 가격, 카테고리 값 존재 유무
	 * <p>등록된 카테고리 확인
	 * <p>상품명 중복 확인
	 * @param newGoods
	 * @return
	 */
	public StatusCode validationCheck(Goods newGoods) {
		StatusCode result = newGoods.checkValue();
		if(result.isSuccess()) {
			Category category = newGoods.getCategory();
			if(!categoryChecker.loadCategoryAndcheckExistCategoryName(category))
				result = StatusCode.NONE_VALUE.setExtraMessage(String.format("%s 카테고리", category.getName()));
			if(result.isSuccess())
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
