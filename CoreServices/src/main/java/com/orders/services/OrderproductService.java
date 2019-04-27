package com.orders.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.dao.interfaces.CurrencyDaoInterface;
import com.orders.domain.model.dao.interfaces.OrderDaoInterface;
import com.orders.domain.model.dao.interfaces.OrderproductDaoInterface;
import com.orders.domain.model.dao.interfaces.ProductDaoInterface;
import com.orders.domain.model.dao.interfaces.StockDaoInterface;
import com.orders.domain.model.entity.Currency;
import com.orders.domain.model.entity.Order;
import com.orders.domain.model.entity.Product;
import com.orders.domain.model.entity.Stock;
import com.orders.model.OrderproductBinding;
import com.orders.domain.model.entity.Orderproduct;

@Service
public class OrderproductService {
	@Autowired
	private ProductDaoInterface productDao;
	
	@Autowired
	private OrderproductDaoInterface orderproductDao;
	
	@Autowired
	private StockDaoInterface stockproductDao;
	
	@Autowired
	private OrderDaoInterface orderDao;
	
	@Autowired
	private CurrencyDaoInterface currencyDao;
	
	
    public Orderproduct find(int productId) {
        return orderproductDao.findById(productId);
    }

	public int add(OrderproductBinding orderproductBinding) {
    	if(orderproductBinding.getQuanty() < 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Quantity to order must be gt 0");
    	}
    	if(orderproductBinding.getOrderId() == 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Order");
    	}
    	Order orderDB = orderDao.findById(orderproductBinding.getOrderId());
    	if (orderDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Order");
    	}
    	if(orderproductBinding.getProductId() == 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Product");
    	}
    	Product productDB = productDao.findById(orderproductBinding.getProductId());
    	if (productDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Product");
    	}
    	Stock stock = stockproductDao.findByProduct(productDB.getProductId());
    	if(stock == null || stock.getQuanty() <= 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is no stock of the product "+productDB.getProductId());
    	}
    	if(orderproductBinding.getQuanty() > stock.getQuanty()) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is no enough stock of the product: "+productDB.getProductId() +" for the order");
    	}
    	
    	Orderproduct orderproduct = new Orderproduct();
    	orderproduct.setQuanty(orderproductBinding.getQuanty());
    	orderproduct.setPrice(Double.parseDouble(productDB.getPrice()));
    	orderproduct.setCurrencyIso(productDB.getCurrencyIso());
    	orderproduct.setProduct(productDB);
    	orderproduct.setOrder(orderDB);
    	
    	int id = (int) orderproductDao.addProductStock(orderproduct,stock);
    	
    	if (id == 0) { //Transaction ERROR
    		throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Transaction was not completed");
    	}
    	
    	flushSession();
    	return id;
	}

	public void delete(OrderproductBinding orderproductBinding) {
		
    	if(orderproductBinding.getOrderId() == 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Order");
    	}
		if (orderproductBinding.getProductId() == 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Product");
		}
		List<Orderproduct> orderproductsDB = orderproductDao.findOrderproduct(orderproductBinding.getOrderId(), orderproductBinding.getProductId());
		Stock stock = stockproductDao.findByProduct(orderproductBinding.getProductId());

		for (Orderproduct orderproductDB : orderproductsDB) {
			orderproductDao.deleteProductStock(orderproductDB,stock);
		}
		
	}
	
	public Orderproduct update(OrderproductBinding orderproductBinding, int orderproductId) {
    	
		Orderproduct orderproductDB = find(orderproductId);
    	
    	if (orderproductDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	
    	if (orderproductBinding.getCurrencyIso() != null) {
	    	Currency currency = currencyDao.findByCurrencyIso(orderproductBinding.getCurrencyIso());
	    	if (currency == null || !currency.isEnable()) {
	    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Currency");
	    	}
	    	orderproductDB.setCurrencyIso(orderproductBinding.getCurrencyIso());
    	}
    	
    	orderproductDB.setCurrencyIso(orderproductBinding.getCurrencyIso() != null && !orderproductBinding.getCurrencyIso().equals("") ? orderproductBinding.getCurrencyIso() : orderproductDB.getCurrencyIso());
    	orderproductDB.setPrice(orderproductBinding.getPrice() > 0.0 ? orderproductBinding.getPrice() : orderproductDB.getPrice());
    	
    	orderproductDao.update(orderproductDB);
    	
    	return orderproductDB;
	}
	
	public void flushSession() {
		orderproductDao.flush();
	}



}
