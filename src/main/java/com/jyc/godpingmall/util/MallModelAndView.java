package com.jyc.godpingmall.util;

import org.springframework.web.servlet.ModelAndView;

public class MallModelAndView extends ModelAndView {

    public MallModelAndView setViewPage(String viewPage) {
        super.setViewName("/mall/frame/main");
        super.addObject("viewPage", String.format("/mall/%s.jsp", viewPage));
        return this;
    }
}
