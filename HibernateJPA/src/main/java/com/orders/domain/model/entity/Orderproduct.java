package com.orders.domain.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
@Table(name="orderproduct",catalog="orderdb")
public class Orderproduct implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OrderProductID")
	private int orderProductId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Order_OrderID", nullable=false)
	@JsonIgnore
	private Order order;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Product_ProductID")
	private Product product;
	
	@Column(name="Price", nullable=false)
	private double price;
	
	@Column(name="Quanty", nullable=false)
	private int quanty;
	
	@Column(name="CurrencyISO", nullable=false)
	private String currencyIso;

	public Orderproduct() {
	}

	public Orderproduct(int orderProductId, Order order, Product product, double price, int quanty,
			String currencyIso) {
		this.orderProductId = orderProductId;
		this.order = order;
		this.product = product;
		this.price = price;
		this.quanty = quanty;
		this.currencyIso = currencyIso;
	}
	
	public int getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}
	
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuanty() {
		return this.quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	
	public String getCurrencyIso() {
		return this.currencyIso;
	}

	public void setCurrencyIso(String currencyIso) {
		this.currencyIso = currencyIso;
	}

}
