package com.capgemini.productapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.AuthenticationFailedException;
import com.capgemini.productapp.exception.CustomerNotFoundException;
import com.capgemini.productapp.exception.ProductNotFoundException;

@ControllerAdvice
public class ExceptionController  {
	
	private static final Logger logs=LoggerFactory.getLogger(Exception.class);

	@ExceptionHandler(value=AuthenticationFailedException.class)
		public String authenticateException() {
			return" ";
		}
	
		@ExceptionHandler(value=CustomerNotFoundException.class)
			public String customerException() {
				return "";
			}
		
		@ExceptionHandler(value = ProductNotFoundException.class)
		public ResponseEntity<Product> productNotFoundException(ProductNotFoundException e){
			System.out.println(e);
			System.out.println(e.getCause());
			logs.info("Product dont exist");
			logs.info("Product dont exist"+e);
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}

