package com.orders.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.orders.domain.model.entity.Orderproduct;

public class OrderEvaluate implements Serializable {
	
	private static final long serialVersionUID = -7788665165133712L;
	
	private int orderId;
	private Date date;
	private Set<Orderproduct> orderproducts = new HashSet<Orderproduct>(0);
	private double total;
	private String currency;

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Set<Orderproduct> getOrderproducts() {
		return orderproducts;
	}
	public void setOrderproducts(Set<Orderproduct> orderproducts) {
		this.orderproducts = orderproducts;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
