package com.orders.domain.model.dao.interfaces;

import com.orders.domain.model.dao.base.DaoInterface;
import com.orders.domain.model.entity.Stock;


public interface StockDaoInterface extends DaoInterface<Integer, Stock> {

	public Stock findByProduct(int productId);

}
