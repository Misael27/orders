package com.orders.domain.model.dao.interfaces;
import java.util.List;

import com.orders.domain.model.dao.base.DaoInterface;
import com.orders.domain.model.entity.Orderproduct;
import com.orders.domain.model.entity.Stock;


public interface OrderproductDaoInterface extends DaoInterface<Integer, Orderproduct> {
	public int addProductStock(Orderproduct orderproduct, Stock stock);
	public void deleteProductStock(Orderproduct orderproduct, Stock stock);
	public List<Orderproduct> findOrderproduct(int orderId, int productId);
}
