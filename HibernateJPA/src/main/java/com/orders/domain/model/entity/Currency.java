package com.orders.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="currency",catalog="orderdb")
public class Currency implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CurrencyID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer currencyId;
	
	@Column(name="CurrencyISO", nullable=false)
	private String currencyIso;
	
	@Column(name="Enable", nullable=false)
	private boolean enable;

	public Currency() {
	}

	public Currency(String currencyIso, boolean enable) {
		this.currencyIso = currencyIso;
		this.enable = enable;
	}

	public Integer getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyIso() {
		return this.currencyIso;
	}

	public void setCurrencyIso(String currencyIso) {
		this.currencyIso = currencyIso;
	}

	public boolean isEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
