package com.orders.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.orders.domain.model.dao.interfaces.CurrencyDaoInterface;
import com.orders.domain.model.dao.interfaces.OrderDaoInterface;
import com.orders.domain.model.entity.Currency;
import com.orders.domain.model.entity.Order;
import com.orders.domain.model.entity.Orderproduct;
import com.orders.model.OrderEvaluate;

@Service
public class OrderService {
	@Autowired
	private OrderDaoInterface orderDao;
	
	@Autowired
	private CurrencyDaoInterface currencyDao;

	public List<Order> findAll() {
		return orderDao.findAll();
	}

	public Order find(int orderId) {
        return orderDao.findById(orderId);
	}

	public int add(Order order) {
		Date today = Calendar.getInstance().getTime();
		order.setDate(today);
    	int id = (int) orderDao.save(order);
    	flushSession();
    	return id;
	}

	public void delete(int orderId) {
    	Order orderDB = find(orderId);
    	if (orderDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	orderDao.delete(orderDB);
	}

	public OrderEvaluate evaluate(int orderId, String currencyIso) {
		
		if (currencyIso == null || currencyIso.equals("")) {
			currencyIso = "USD"; //Default Currency
		}
		Currency currency = currencyDao.findByCurrencyIso(currencyIso);
    	if (currency == null || !currency.isEnable()) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Currency");
    	}
    	
    	Order order = find(orderId);
    	double totalOrder = 0.0;
    	
    	Set<Orderproduct> orderproducts =  order.getOrderproducts();
    	
    	JSONObject ratesjson = new JSONObject(); 
    	
    	final String uri = "http://data.fixer.io/api/latest?access_key=59b23857498df6da79846ef2a4819e3b";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    JSONParser parser = new JSONParser();
	    try {
			JSONObject json = (JSONObject) parser.parse(result);
			ratesjson = (JSONObject) json.get("rates");
		} catch (ParseException e) {
			e.printStackTrace();
		}

    	double value = 0.0;
    	double toRate = !currency.getCurrencyIso().equals("EUR") ? (double)ratesjson.get(currency.getCurrencyIso()) : 1.0;
    	double fromRate = 0.0;
    	
    	for (Orderproduct orderproduct : order.getOrderproducts()) {
    		if (!currency.getCurrencyIso().equals(orderproduct.getCurrencyIso())) {
        		fromRate = !orderproduct.getCurrencyIso().equals("EUR") ? (double) ratesjson.get(orderproduct.getCurrencyIso()) : 1.0;
        		value = (orderproduct.getPrice() / fromRate) * toRate;
        		orderproduct.setCurrencyIso(currency.getCurrencyIso());
        		orderproduct.setPrice(value);
    		}
    		else {
    			value = orderproduct.getPrice();
    		}
    		totalOrder += value;
    		value = 0.0;
    	}
    	
    	OrderEvaluate orderEvaluate = new OrderEvaluate();
    	orderEvaluate.setOrderId(order.getOrderId());
    	orderEvaluate.setDate(order.getDate());
    	orderEvaluate.setOrderproducts(orderproducts);
    	orderEvaluate.setTotal(totalOrder);
    	orderEvaluate.setCurrency(currency.getCurrencyIso());
    	
		return orderEvaluate;
		
	}
	
	public void flushSession() {
		orderDao.flush();
	}


}
