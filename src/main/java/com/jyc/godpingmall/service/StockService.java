package com.jyc.godpingmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jyc.godpingmall.status.enums.StatusCode;
import com.jyc.godpingmall.vo.GoodsOption;

@Service
public class StockService {

	public StatusCode insertNewStock(List<GoodsOption> goodsOptionList) {
		return StatusCode.SUCCESS;
	}
}
