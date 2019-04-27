package com.orders.services;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.dao.interfaces.CurrencyDaoInterface;
import com.orders.domain.model.dao.interfaces.ProductDaoInterface;
import com.orders.domain.model.entity.Currency;
import com.orders.domain.model.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDaoInterface productDao;
	
	@Autowired
	private CurrencyDaoInterface currencyDao;
	
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
    public Product find(int productId) {
        return productDao.findById(productId);
    }
	
    public int add(Product product) {
    	Currency currency = currencyDao.findByCurrencyIso(product.getCurrencyIso());
    	if (currency == null || !currency.isEnable()) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Currency");
    	}
    	if (product.getPrice() == null || product.getPrice().equals("") || Double.parseDouble(product.getPrice()) < 0.0 ) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Price");
    	}
    	if (product.getName() == null || product.getName().equals("")) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Name");
    	}
    	if (product.getSku() == null || product.getSku().equals("")) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Sku");
    	}
    	Product productSKU = productDao.findBySku(product.getSku()); 
    	if (productSKU != null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Sku, it must be unique");
    	}
    	int id = (int) productDao.save(product);
    	flushSession();
    	return id;
    }
 
    public Product update(Product product, int productId) {
    	Product productDB = find(productId);
    	
    	if (productDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	
    	if (product.getCurrencyIso() != null) {
	    	Currency currency = currencyDao.findByCurrencyIso(product.getCurrencyIso());
	    	if (currency == null || !currency.isEnable()) {
	    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Currency");
	    	}
	    	productDB.setCurrencyIso(product.getCurrencyIso());
    	}

    	productDB.setName(product.getName() !=null && !product.getName().equals("")  ? product.getName() : productDB.getName());
    	productDB.setPrice(product.getPrice() !=null && !product.getPrice().equals("") && Double.parseDouble(product.getPrice()) >= 0.0 ? product.getPrice() : productDB.getPrice());
    	productDB.setSku(product.getSku() !=null && !product.getSku().equals("")  ? product.getSku() : productDB.getSku());
    	
    	if (Double.parseDouble(productDB.getPrice()) <= 0.0) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Price");
    	}
    	
    	productDao.update(productDB);
    	
    	return productDB;
    }
 
    public void delete(int productId) {
    	Product productDB = find(productId);
    	if (productDB == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    	}
    	productDao.delete(productDB);
    }
	
	public void flushSession() {
		productDao.flush();
	}
	
}
