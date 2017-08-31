package com.jyc.godpingmall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.OptionService;
import com.jyc.godpingmall.util.AdminModelAndView;

@RestController
@RequestMapping(value = "/admin", produces = "application/json; charset=UTF-8")
public class OptionController {
	
	@Autowired private OptionService optionService;

	@RequestMapping(value = "/option", method = RequestMethod.POST)
	public StatusCode addNewCategory(String optionName) {
		return optionService.addNewOption(optionName);
	}
	
	@RequestMapping(value = "/option", method = RequestMethod.GET)
	public AdminModelAndView getCategoryMap() {
		AdminModelAndView mav = new AdminModelAndView();
		mav.addObject("optionList", optionService.getOptionList());
		mav.setViewPage("option/optionList");
		return mav;
	}
}
