package com.orders.model;

import java.io.Serializable;

public class OrderproductBinding implements Serializable {
	
	private static final long serialVersionUID = -7788619177798333712L;
	
	private int orderId;
	private int productId;
	private int quanty;
	private double price;
	private String currencyIso;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrencyIso() {
		return currencyIso;
	}
	public void setCurrencyIso(String currencyIso) {
		this.currencyIso = currencyIso;
	}
}
