package com.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orders.domain.model.entity.Currency;
import com.orders.services.CurrencyService;


@RestController
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public @ResponseBody List<Currency> getAll() {
		logger.info("Get All Currency.");
		List<Currency> currencies = currencyService.findAll();
		return currencies;
	}
	
	@RequestMapping(value = "/currency/enable", method = RequestMethod.PUT)
	public Currency Enable(@RequestParam(value ="currencyIso", required=true) String currencyIso) {
		logger.info("Start updateProduct.");
		return currencyService.setEnable(currencyIso, true);
	}
	
	@RequestMapping(value = "/currency/disable", method = RequestMethod.PUT)
	public Currency Disable(@RequestParam(value ="currencyIso", required=true) String currencyIso) {
		logger.info("Start updateProduct.");
		return currencyService.setEnable(currencyIso, false);
	}
	
}
