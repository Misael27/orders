package com.orders.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.orders.domain.model.dao.interfaces.CurrencyDaoInterface;
import com.orders.domain.model.entity.Currency;


@Service
public class CurrencyService {
	@Autowired
	private CurrencyDaoInterface currencyDao;
	
	public List<Currency> findAll(){
		return currencyDao.findAll();
	}

	public void flushSession() {
		currencyDao.flush();
	}

	public Currency setEnable(String currencyIso, boolean b) {
		
    	Currency currency = currencyDao.findByCurrencyIso(currencyIso);
    	if (currency == null) {
    		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Invalid Currency");
    	}
    	
    	currency.setEnable(b);

    	currencyDao.update(currency);
		return currency;
	}
}
