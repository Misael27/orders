package com.orders.domain.model.dao.interfaces;

import com.orders.domain.model.dao.base.DaoInterface;
import com.orders.domain.model.entity.Product;


public interface ProductDaoInterface extends DaoInterface<Integer, Product> {
	public Product findBySku(String sku);
}
