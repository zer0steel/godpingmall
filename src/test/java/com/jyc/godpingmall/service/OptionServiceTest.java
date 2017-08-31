package com.jyc.godpingmall.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jyc.godpingmall.dao.OptionDAO;
import com.jyc.godpingmall.enums.StatusCode;
import com.jyc.godpingmall.service.checker.OptionChecker;

@RunWith(MockitoJUnitRunner.class)
public class OptionServiceTest {
	
	@Inject private OptionDAO optionDao = mock(OptionDAO.class);
	@Inject private OptionChecker optionChecker = mock(OptionChecker.class);
	@InjectMocks private OptionService optionService;

	@Test
	public void insert_option_should_be_success() {
		String optionName = "옵션";
		
		when(optionChecker.loadOptionAndValidationCheck(optionName)).thenReturn(StatusCode.SUCCESS);
		
		StatusCode result = optionService.addNewOption(optionName);
		
		assertEquals(StatusCode.SUCCESS, result);
	}
	
	@Test
	public void insert_option_should_be_fail_when_check_is_not_pass() {
		String optionName = "옵션";
		
		when(optionChecker.loadOptionAndValidationCheck(optionName)).thenReturn(StatusCode.OVERLAP_VALUE);
		
		StatusCode result = optionService.addNewOption(optionName);
		
		assertNotEquals(StatusCode.SUCCESS, result);
	}
	
}
