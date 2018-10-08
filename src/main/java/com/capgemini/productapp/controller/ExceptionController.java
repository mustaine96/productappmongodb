package com.capgemini.productapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.productapp.exception.AuthenticationFailedException;
import com.capgemini.productapp.exception.CustomerNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=AuthenticationFailedException.class)
		public String authenticateException() {
			return" ";
		}
	
		@ExceptionHandler(value=CustomerNotFoundException.class)
			public String customerException() {
				return "";
			}
		
	}

