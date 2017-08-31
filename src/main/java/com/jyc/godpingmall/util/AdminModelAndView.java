package com.jyc.godpingmall.util;

import org.springframework.web.servlet.ModelAndView;

public class AdminModelAndView extends ModelAndView {

    public AdminModelAndView setViewPage(String viewPage) {
        super.setViewName("/admin/frame/main");
        super.addObject("viewPage", String.format("/admin/%s.jsp", viewPage));
        return this;
    }
}
