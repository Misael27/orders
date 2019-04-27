package com.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.entity.Stock;
import com.orders.services.StockService;

@RestController
public class StockController {
	@Autowired
	private StockService stockService;
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public List<Stock> GetAll() {
		logger.info("Get All stocks.");
		List<Stock> stocks = stockService.findAll();
		return stocks;
	}
	
	@RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
	public Stock Get(@PathVariable("id") int stockId) {
		logger.debug("Start Stock. ID="+stockId);
		Stock stock = stockService.find(stockId);
		if (stock == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return stock;
	}
	
	@RequestMapping(value = "/stock/product/{id}", method = RequestMethod.GET)
	public Stock GetByProduct(@PathVariable("id") int productId) {
		logger.debug("Start Product. ID="+productId);
		Stock stock = stockService.findByProduct(productId);
		if (stock == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return stock;
	}
	
	@RequestMapping(value = "/stock", method = RequestMethod.POST)
	public Stock Create(@RequestBody Stock stock) {
		logger.info("Start stock.");
		int id = stockService.add(stock);
		return stockService.find(id);
	}
	
	@RequestMapping(value = "/stock/{id}", method = RequestMethod.PUT)
	public Stock Update(@RequestBody Stock stock, @PathVariable("id") int stockId) {
		logger.info("Start updateStock.");
		return stockService.update(stock, stockId);
	}
	
	@RequestMapping(value = "/stock/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void Delete(@PathVariable("id") int stockId) {
		logger.info("Start deleteStock.");
		stockService.delete(stockId);
	}

}
