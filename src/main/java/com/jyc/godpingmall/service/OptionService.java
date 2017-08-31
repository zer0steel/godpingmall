package com.jyc.godpingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.godpingmall.dao.OptionDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.OptionChecker;

@Service
public class OptionService {
	
	@Autowired private OptionDAO optionDao;
	@Autowired private OptionChecker optionChecker;

	/**
	 * 신규 옵션 등록
	 * @param optionName
	 * @return {@link StatusCode}
	 */
	public StatusCode addNewOption(String optionName) {
		StatusCode result = optionChecker.loadOptionAndValidationCheck(optionName);
		if(result.isSuccess())
			optionDao.insertNewOption(optionName);
		return result;
	}

	public List<String> getOptionList() {
		return optionDao.getOptionList();
	}
}
