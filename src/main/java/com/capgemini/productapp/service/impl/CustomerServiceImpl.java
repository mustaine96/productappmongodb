package com.capgemini.productapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Customer;
import com.capgemini.productapp.exception.AuthenticationFailedException;
import com.capgemini.productapp.exception.CustomerNotFoundException;
import com.capgemini.productapp.repository.CustomerRepository;
import com.capgemini.productapp.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(Customer customer) {
	
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
	
		return customerRepository.save(customer);
	}

	@Override
	public Customer findCustomerById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer= customerRepository.findById(customerId);
		try {	
		return optionalCustomer.get();}
		catch(DataAccessException e) {
		
			CustomerNotFoundException custNotFound=new CustomerNotFoundException("Customer Dont Exist");
			custNotFound.initCause(e);
			throw custNotFound;
	
		
		}}


	

	@Override
	public Customer authenticate(Customer customer) throws AuthenticationFailedException, CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById((int) customer.getCustomerId());
		if (!customer1.isPresent())
			throw new CustomerNotFoundException("Customer Not found");
		if (customer1.isPresent()) {
			if (customer1.get().getCustomerPassword().equals(customer.getCustomerPassword()))
				return customer1.get();
		}
		throw new AuthenticationFailedException("Login Failed Try again");
	
	}

	@Override
	public void deleteCustomer(Customer customer) throws CustomerNotFoundException {
		customerRepository.delete(customer);
		
	}


}
