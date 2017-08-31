package com.jyc.godpingmall.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jyc.godpingmall.service.GoodsService;
import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.util.AdminModelAndView;
import com.jyc.godpingmall.vo.Goods;

@RestController("AdminGoodsController")
@RequestMapping(value = "/admin/", produces = "application/json; charset=UTF-8")
public class GoodsController {
	
	@Autowired private GoodsService goodsSerivce;

	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	public StatusCode addNewCategory(Goods newGoods) {
		return goodsSerivce.addNewGoods(newGoods);
	}
	
	@RequestMapping(value = "/goods/insert", method = RequestMethod.GET)
	public AdminModelAndView addPage() {
		return new AdminModelAndView().setViewPage("goods/goodsInsert");
	}
	
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public AdminModelAndView getCategoryMap() {
		AdminModelAndView mav = new AdminModelAndView();
		mav.addObject("goodsMap", goodsSerivce.getGoodsMap());
		return mav.setViewPage("goods/goodsList");
	}
}
