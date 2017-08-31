package com.jyc.godpingmall.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.GoodsOptionDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.OptionChecker;
import com.jyc.godpingmall.vo.GoodsOption;

@Service
public class GoodsOptionService {
	
	@Autowired private GoodsOptionDAO goodsOptionDao;
	@Autowired private OptionChecker optionChecker;
	
	/**
	 * 특정상품에 신규 옵션 등록
	 * @param goodsOptionList
	 * @return
	 */
	public StatusCode addNewGoodsOption(List<GoodsOption> goodsOptionList) {
		StatusCode result = optionChecker.checkGoodsOptionList(goodsOptionList);
		if(result.isSuccess())
			goodsOptionDao.insertNewGoodsOption(goodsOptionList);
		return result;
	}
}
