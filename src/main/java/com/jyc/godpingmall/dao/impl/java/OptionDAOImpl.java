package com.jyc.godpingmall.dao.impl.java;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jyc.godpingmall.dao.OptionDAO;

@Repository
class OptionDAOImpl implements OptionDAO {
	
	private List<String> list = new ArrayList<>();

	@Override
	public void insertNewOption(String optionName) {
		list.add(optionName);
	}

	@Override
	public List<String> getOptionList() {
		return list;
	}
	
}
