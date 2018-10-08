package com.capgemini.productapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;

public interface ProductRepository extends MongoRepository<Product, Integer>{


}
