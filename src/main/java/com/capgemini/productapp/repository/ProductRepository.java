package com.capgemini.productapp.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;

public interface ProductRepository extends MongoRepository<Product, Integer>{


	@Query("{ $productName: ?0}")
	public List<Product> findProductByName(String productName) throws ProductNotFoundException;


}
