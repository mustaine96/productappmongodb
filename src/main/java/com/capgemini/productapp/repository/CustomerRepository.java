package com.capgemini.productapp.repository;




import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.productapp.entity.Customer;


public interface CustomerRepository extends MongoRepository<Customer, Integer> {


}
