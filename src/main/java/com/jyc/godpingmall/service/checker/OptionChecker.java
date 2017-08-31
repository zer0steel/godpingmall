package com.jyc.godpingmall.service.checker;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.OptionDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.vo.GoodsOption;
import com.jyc.godpingmall.vo.ValidationChecker;

@Service
public class OptionChecker {
	
	@Autowired private OptionDAO optionDao;
	private List<String> optionList;
	
	/**
	 * 옵션 목록 불러오기
	 */
	private void loadOptionList() {
		optionList = optionDao.getOptionList();
	}
	
	/**
	 * 옵션 목록을 불러오고 유효성 체크 시작
	 * @param optionName 확인하려는 옵션 이름
	 * @return
	 */
	public StatusCode loadOptionAndValidationCheck(String optionName) {
		loadOptionList();
		return checkOptionName(optionName);
	}

	private StatusCode checkOptionName(String optionName) {
		if(ValidationChecker.isEmpty(optionName))
			return StatusCode.EMPTY_VALUE.setExtraMessage("옵션이름");
		
		if(isExistOptionName(optionName))
			return StatusCode.OVERLAP_VALUE.setExtraMessage(optionName);
		return StatusCode.SUCCESS;
	}

	/**
	 * 이미 존재하는 옵션인지 확인
	 * @param optionName 확인하려는 옵션 이름
	 * @return <p>true : 옵션 이름 존재함
	 */
	private boolean isExistOptionName(String optionName) {
		return optionList.contains(optionName);
	}
	
	public StatusCode checkGoodsOptionList(List<GoodsOption> goodsOptionList) {
		if(ValidationChecker.isEmpty(goodsOptionList))
			return StatusCode.SUCCESS;
		loadOptionList();
		for (GoodsOption goodsOption : goodsOptionList) {
			StatusCode result = checkGoodsOption(goodsOption);
			if(result.isNotSuccess())
				return result;
		}
		return StatusCode.SUCCESS;
	}
	
	/**
	 * 옵션이름이 존재하는지 확인
	 * @param goodsOption
	 * @return {@link StatusCode}
	 */
	private StatusCode checkGoodsOption(GoodsOption goodsOption) {
		StatusCode result = goodsOption.checkValue();
		if(result.isNotSuccess())
			return result;
		if(!isExistOptionName(goodsOption.getOptionName()))
			return StatusCode.NONE_VALUE.setExtraMessage(goodsOption.getOptionName());
		return StatusCode.SUCCESS;
	}
}
