package com.jyc.godpingmall.dao;

import java.util.List;

public interface OptionDAO {

	void insertNewOption(String optionName);

	List<String> getOptionList();
}
