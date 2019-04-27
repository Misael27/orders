package com.orders.domain.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;


@Entity
@Table(name="stock",catalog="orderdb")
public class Stock implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="StockID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Product_ProductID")
	private Product product;
	
	@Column(name="Quanty", nullable=false)
	private int quanty;

	public Stock() {
	}

	public Stock(int stockId, Product product, int quanty) {
		this.stockId = stockId;
		this.product = product;
		this.quanty = quanty;
	}
	
	public int getStockId() {
		return this.stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuanty() {
		return this.quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

}
