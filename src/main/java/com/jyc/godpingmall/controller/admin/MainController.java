package com.jyc.godpingmall.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jyc.godpingmall.util.AdminModelAndView;

@RestController("AdminMainController")
@RequestMapping(value = "/admin", produces = "application/json; charset=UTF-8")
public class MainController {
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public AdminModelAndView addNewCategory(String optionName) {
		return new AdminModelAndView().setViewPage("front");
	}
	
}
