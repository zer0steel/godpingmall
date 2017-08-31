package com.jyc.godpingmall.controller.all;

import com.jyc.godpingmall.util.MallModelAndView;
import com.jyc.godpingmall.vo.Member.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController()
public class LoginController {

	@RequestMapping(value = "/login")
	public ModelAndView login() {
        System.out.println("로그인 페이지");
        return new MallModelAndView().setViewPage("/public/member/login");
	}
}
