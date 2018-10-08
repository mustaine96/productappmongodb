package com.capgemini.productapp.service;

import com.capgemini.productapp.entity.Customer;
import com.capgemini.productapp.exception.AuthenticationFailedException;
import com.capgemini.productapp.exception.CustomerNotFoundException;


public interface CustomerService {
	
public Customer authenticate(Customer customer) throws AuthenticationFailedException, CustomerNotFoundException;
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	public Customer findCustomerById(int customerId)throws CustomerNotFoundException;
	
	public void deleteCustomer(Customer customer)throws CustomerNotFoundException;


}
