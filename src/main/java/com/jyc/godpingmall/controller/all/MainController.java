package com.jyc.godpingmall.controller.all;

import com.jyc.godpingmall.util.MallModelAndView;
import com.jyc.godpingmall.vo.Category;

import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("MainController")
public class MainController {

	@RequestMapping("/main")
	public ModelAndView main() {
		return new MallModelAndView().setViewPage("/front");
	}
}
