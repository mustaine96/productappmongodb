package com.capgemini.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Customer;
import com.capgemini.productapp.exception.AuthenticationFailedException;
import com.capgemini.productapp.exception.CustomerNotFoundException;
import com.capgemini.productapp.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	 private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer (@RequestBody Customer customer){
		
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
		
		return responseEntity;
		
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer (@RequestBody Customer customer) throws CustomerNotFoundException{
		
		Customer customer1 = customerService.findCustomerById(customer.getCustomerId());
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> findCustomerById (@PathVariable int customerId) throws CustomerNotFoundException{
		
		Customer customer1 = customerService.findCustomerById(customerId);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.findCustomerById(customerId), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	

	}
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException{

		Customer customer1 = customerService.findCustomerById(customerId);
		if (customer1 != null) {
			customerService.deleteCustomer(customer1);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
		
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) throws AuthenticationFailedException, CustomerNotFoundException{
		
		Customer customer1 = customerService.authenticate(customer);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.authenticate(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	
	}
	
	
}
