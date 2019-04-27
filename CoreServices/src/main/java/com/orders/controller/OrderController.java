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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.entity.Order;
import com.orders.domain.model.entity.Orderproduct;
import com.orders.model.OrderproductBinding;
import com.orders.model.OrderEvaluate;
import com.orders.services.OrderService;
import com.orders.services.OrderproductService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderproductService orderproductService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public List<Order> GetAll() {
		logger.info("Get All Orders.");
		List<Order> orders = orderService.findAll();
		return orders;
	}
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public Order Get(@PathVariable("id") int orderId) {
		logger.debug("Start Order. ID="+orderId);
		Order order = orderService.find(orderId);
		if (order == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return order;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Order Create(@RequestBody Order order) {
		logger.info("Start createOrder.");
		int id = orderService.add(order);
		return orderService.find(id);
	}
	
	@RequestMapping(value = "/order/{id}/evaluate", method = RequestMethod.GET)
	public OrderEvaluate Evaluate(@PathVariable("id") int orderId , @RequestParam(value ="currencyIso", required=false) String currencyIso) {
		logger.debug("Start Order Evaluate. ID="+orderId+" with currency: "+currencyIso);
		OrderEvaluate order = orderService.evaluate(orderId, currencyIso);
		if (order == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return order;
	}
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void Delete(@PathVariable("id") int orderId) {
		logger.info("Start deleteProduct.");
		orderService.delete(orderId);
	}
	
	@RequestMapping(value = "/order/product", method = RequestMethod.POST)
	public Order CreateOrderProduct(@RequestBody OrderproductBinding orderproductBinding) {
		logger.info("Start createOrder.");
		orderproductService.add(orderproductBinding);
		return orderService.find(orderproductBinding.getOrderId());
	}
	
	@RequestMapping(value = "/order/product/{orderproductId}", method = RequestMethod.PUT)
	public Orderproduct Update(@RequestBody OrderproductBinding orderproductBinding, @PathVariable("orderproductId") int orderproductId) {
		logger.info("Start updateOrder.");
		return orderproductService.update(orderproductBinding,orderproductId);
	}
	
	@RequestMapping(value = "/order/product", method = RequestMethod.DELETE)
	public void DeleteteOrderProduct(@RequestBody OrderproductBinding orderproductBinding) {
		logger.info("Start createOrder.");
		orderproductService.delete(orderproductBinding);
	}
	

}
