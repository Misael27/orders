package com.orders.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.dao.interfaces.ProductDaoInterface;
import com.orders.domain.model.dao.interfaces.StockDaoInterface;
import com.orders.domain.model.entity.Product;
import com.orders.domain.model.entity.Stock;

@Service
public class StockService {
	@Autowired
	private StockDaoInterface stockDao;
	
	@Autowired
	private ProductDaoInterface productDao;

	public List<Stock> findAll() {
		return stockDao.findAll();
	}
	
	public Stock find(int id) {
        return stockDao.findById(id);
	}

	public Stock findByProduct(int productId) {
		return stockDao.findByProduct(productId);
	}

	public int add(Stock stock) {
		
    	if (stock.getProduct() == null || stock.getProduct().getProductId() == 0) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Product");
    	}
    	
    	Product productDB = productDao.findById(stock.getProduct().getProductId());
    	if (productDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	
    	Stock stockDB = stockDao.findByProduct(productDB.getProductId());
    	if(stockDB != null) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Already exist this product in Stock DB");
    	}
    	
    	if (stock.getQuanty() < 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Quanty must be gt zero");
    	}
    	
    	stock.setProduct(productDB);
    	
		int id = (int) stockDao.save(stock);
    	flushSession();
    	return id;
	}

	public Stock update(Stock stock, int stockId) {
		
		Stock stockDB = find(stockId);
    	
    	if (stockDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	
    	if (stock.getQuanty() < 0) {
    		throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Quanty must be gt zero");
    	}
    	
    	stockDB.setQuanty(stock.getQuanty());  	
    	
    	stockDao.update(stockDB);

    	return stockDB;
	}

	public void delete(int stockId) {
    	Stock stockDB = find(stockId);
    	if (stockDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	stockDao.delete(stockDB);
	}
	
	public void flushSession() {
		stockDao.flush();
	}

}
