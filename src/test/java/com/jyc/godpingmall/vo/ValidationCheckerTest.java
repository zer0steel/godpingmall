package com.jyc.godpingmall.vo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValidationCheckerTest {

	@Test
	public void check_string_should_be_success_when_string_is_null() {
		String nullString = null;
		
		boolean result = ValidationChecker.isEmpty(nullString);
		
		assertTrue(result);
	}
	
	@Test
	public void check_string_should_be_success_when_string_is_empty() {
		String emptyString = "";
		
		boolean result = ValidationChecker.isEmpty(emptyString);
		
		assertTrue(result);
	}
	
	@Test
	public void check_string_should_be_fail_when_string_size_is_not_zer0() {
		String string = "실패";
		
		boolean result = ValidationChecker.isEmpty(string);
		
		assertFalse(result);
	}
	
	@Test
	public void check_list_should_be_success_when_list_is_null() {
		List<Object> nullList = null;
		
		boolean result = ValidationChecker.isEmpty(nullList);
		
		assertTrue(result);
	}
	
	@Test
	public void check_list_should_be_success_when_list_is_empty() {
		List<Object> emptyList = new ArrayList<>();
		
		boolean result = ValidationChecker.isEmpty(emptyList);
		
		assertTrue(result);
	}
	
	@Test
	public void check_list_should_be_fail_when_list_size_is_not_zer0() {
		List<Object> list = new ArrayList<>();
		list.add(1);
		
		boolean result = ValidationChecker.isEmpty(list);
		
		assertFalse(result);
	}

}
