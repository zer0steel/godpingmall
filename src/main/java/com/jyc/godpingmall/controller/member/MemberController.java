package com.jyc.godpingmall.controller.member;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MyInfoController")
@RequestMapping("/my")
public class MemberController {

	@RequestMapping("/info")
	public void test() {
		System.out.println("개인 정보 페이지");
	}
}
