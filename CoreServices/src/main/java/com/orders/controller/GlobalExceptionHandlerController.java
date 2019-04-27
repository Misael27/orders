package com.orders.controller;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.orders.model.ErrorFormat;


@ControllerAdvice
class GlobalExceptionHandlerController {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<Object> defaultErrorHandler(Exception ex, WebRequest request) {
		HttpStatus code = HttpStatus.BAD_REQUEST;
		String body = "An error has occurred. Please try again";
		if (ex instanceof HttpClientErrorException) {
			HttpClientErrorException e = (HttpClientErrorException)ex;
			code = e.getStatusCode();
			body = e.getMessage();
		}
		else if (ex instanceof DataIntegrityViolationException) {
			body = "ConstraintViolation,Invalid parameters, please check them and try again";
		}
		else if (ex instanceof HttpMessageNotReadableException) {
			body = "Invalid parameters, please check them and try again";
		}
		else {
			ex.printStackTrace();
		}
		return ResponseEntity.status(code).body(new ErrorFormat(body));
	}
	
}