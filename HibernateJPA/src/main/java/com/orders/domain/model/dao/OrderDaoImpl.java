package com.orders.domain.model.dao;

import org.springframework.stereotype.Repository;

import com.orders.domain.model.dao.base.DaoBase;
import com.orders.domain.model.dao.interfaces.OrderDaoInterface;
import com.orders.domain.model.entity.Order;


@Repository(value="orderDao")
public class OrderDaoImpl extends DaoBase<Integer, Order> implements OrderDaoInterface{

}
