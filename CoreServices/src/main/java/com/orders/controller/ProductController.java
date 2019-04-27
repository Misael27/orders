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

import com.orders.domain.model.entity.Product;
import com.orders.services.ProductService;


@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public List<Product> GetAll() {
		logger.info("Get All Products.");
		List<Product> products = productService.findAll();
		return products;
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product Get(@PathVariable("id") int productId) {
		logger.debug("Start Product. ID="+productId);
		Product product = productService.find(productId);
		if (product == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return product;
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public Product Create(@RequestBody Product product) {
		logger.info("Start createProduct.");
		int id = productService.add(product);
		return productService.find(id);
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public Product Update(@RequestBody Product product, @PathVariable("id") int productId) {
		logger.info("Start updateProduct.");
		return productService.update(product, productId);
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void Delete(@PathVariable("id") int productId) {
		logger.info("Start deleteProduct.");
		productService.delete(productId);
	}

}
