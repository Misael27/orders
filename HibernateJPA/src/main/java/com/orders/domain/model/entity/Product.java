package com.orders.domain.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product",catalog="orderdb")
public class Product implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ProductID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	@Column(name="Price", nullable=false)
	private String price;
	
	@Column(name="SKU", nullable=false)
	private String sku;
	
	@Column(name="CurrencyISO", nullable=false)
	private String currencyIso;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Stock> stocks;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Orderproduct> orderproducts = new HashSet<Orderproduct>(0);

	public Product() {
	}

	public Product(int productId, String name, String price, String sku, String currencyIso) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.sku = sku;
		this.currencyIso = currencyIso;
	}

	public Product(int productId, String name, String price, String sku, String currencyIso, Set<Stock> stocks,
			Set<Orderproduct> orderproducts) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.sku = sku;
		this.currencyIso = currencyIso;
		this.stocks = stocks;
		this.orderproducts = orderproducts;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getCurrencyIso() {
		return this.currencyIso;
	}

	public void setCurrencyIso(String currencyIso) {
		this.currencyIso = currencyIso;
	}
	
	public Set<Stock> getStocks() {
		return this.stocks;
	}
	
	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public Set<Orderproduct> getOrderproducts() {
		return this.orderproducts;
	}

	public void setOrderproducts(Set<Orderproduct> orderproducts) {
		this.orderproducts = orderproducts;
	}

}
