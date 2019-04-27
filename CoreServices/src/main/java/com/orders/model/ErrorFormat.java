package com.orders.model;

import java.io.Serializable;

public class ErrorFormat implements Serializable {
	
	private static final long serialVersionUID = -7788619177798333712L;

	private String message;
	
	public ErrorFormat (String message) {
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
