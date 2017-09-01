package com.jyc.godpingmall.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.jyc.godpingmall.vo.GoodsOption;
import com.jyc.godpingmall.vo.Stock;
import com.jyc.godpingmall.vo.ValidationChecker;

/**
 * 옵션값들로 만들수 있는 모든 조합을 만드는 클래스
 */
public class StockGenerator {
	
	private static final int RECURSIVE_START = 0;
	/**
	 * 생성된 재고 목록이 저장되는 리스트
	 */
	private final List<Stock> stockList = new ArrayList<>();
	
	private List<List<GoodsOption>> optionList;
	private Long goodsId;

	/**
	 * 재고 목록을 만든다.
	 * @param goodsOptionList
	 */
	public StockGenerator(List<GoodsOption> goodsOptionList, Long goodsId) {
		this.goodsId = goodsId;
		if(ValidationChecker.isEmpty(goodsOptionList))
			addDefaultStock();
		else
			createStockList(goodsOptionList);
	}
	
	public List<Stock> getResult() {
		return stockList;
	}
	
	/**
	 * 상품 옵션이 하나도 없으므로 기본 옵션으로 생성
	 */
	private void addDefaultStock() {
		stockList.add(createDefaultStock());
	}
	
	/**
	 * 재고 목록을 만든다.
	 * @param goodsOptionList
	 */
	private void createStockList(List<GoodsOption> goodsOptionList) {
		Map<String, List<GoodsOption>> map = createMapForRecursion(goodsOptionList);
		optionList = new ArrayList<>(map.values());
		recursive(RECURSIVE_START, new GoodsOption[map.keySet().size()]);
	}
	
	/**
	 * 재귀 호출 메소드
	 * @param list 모든 옵션 목록
	 * @param deep 재귀 깊이
	 * @param goodsOptionArray 조합 옵션 목록
	 */
	private void recursive(int deep, GoodsOption[] goodsOptionArray) {
		optionList.get(deep).forEach(option -> {
			goodsOptionArray[deep] = option;
			if(isRemainingRecursive(deep))
				recursive(deep + 1, goodsOptionArray);
			else
				stockList.add(createStock(goodsOptionArray));
		});
	}
	
	/**
	 * 재귀가 안끝났는지 확인
	 * @param deep 재귀 깊이
	 * @return <p>true : 재귀가 안끝났음
	 */
	private boolean isRemainingRecursive(int deep) {
		return deep < optionList.size() - 1;
	}

	/**
	 * <p>조합 기준이 되는 옵션이름({@link GoodsOption#getOptionName})이 배열안 객체에 중복으로 들어있음
	 * <p>옵션이름을 기준으로 맵을 생성한다.
	 * @param goodsOptionList
	 * @return Map&lt;{@link GoodsOption#getOptionName}, {@link GoodsOption}&gt;
	 */
	private Map<String, List<GoodsOption>> createMapForRecursion(List<GoodsOption> goodsOptionList) {
		Map<String, List<GoodsOption>> map = new HashMap<>();
		goodsOptionList.forEach(option -> {
			List<GoodsOption> list = map.get(option.getOptionName());
			if(Objects.isNull(list)) {
				list = new ArrayList<>();
				map.put(option.getOptionName(), list);
			}
			list.add(option);
		});
		return map;
	}
	
	/**
	 * <p>기본 재고로 생성
	 * <p>추가가격 : 0원
	 * <p>이름 : 기본옵션
	 * @return
	 */
	private Stock createDefaultStock() {
		Stock defaultStock = new Stock();
		defaultStock.setExtraPrice(BigDecimal.ZERO);
		defaultStock.setName("기본옵션");
		defaultStock.setGoodsId(goodsId);
		return defaultStock;
	}
	
	/**
	 * 옵션을 기반으로 재고 생성
	 * @param goodsOption
	 * @return
	 */
	private Stock createStock(GoodsOption[] goodsOption) {
		Stock stock = new Stock();
		stock.setStock(goodsOption);
		stock.setId(goodsId);
		return stock;
	}
}
