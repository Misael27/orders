package com.orders.domain.model.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="order",catalog="orderdb")
public class Order implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="OrderID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date", nullable=false)
	private Date date;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Orderproduct> orderproducts = new HashSet<Orderproduct>(0);

	public Order() {
	}

	public Order(int orderId, Date date) {
		this.orderId = orderId;
		this.date = date;
	}

	public Order(int orderId, Date date, Set<Orderproduct> orderproducts) {
		this.orderId = orderId;
		this.date = date;
		this.orderproducts = orderproducts;
	}
	
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Set<Orderproduct> getOrderproducts() {
		return this.orderproducts;
	}
	
	public void setOrderproducts(Set<Orderproduct> orderproducts) {
		this.orderproducts = orderproducts;
	}

}
