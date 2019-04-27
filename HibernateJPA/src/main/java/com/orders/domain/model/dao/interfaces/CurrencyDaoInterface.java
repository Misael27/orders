package com.orders.domain.model.dao.interfaces;

import com.orders.domain.model.dao.base.DaoInterface;
import com.orders.domain.model.entity.Currency;


public interface CurrencyDaoInterface extends DaoInterface<Integer, Currency> {
	public Currency findByCurrencyIso(String currencyIso);
}
