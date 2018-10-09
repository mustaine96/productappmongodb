package com.capgemini.productapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, HttpServletRequest request) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);
		logger.info(request.getRequestURI()+" "+HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product productFromDb = productService.findProductById(product.getProductId());
		return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);

	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		
			Product productFromDb = productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		
	}
	
	@GetMapping("/products/{productName}")
	public ResponseEntity<List<Product>> findProductByName(@PathVariable String productName) {
		
			List<Product> productFromDb = productService.findProductByName(productName);
			return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);
		
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
			Product productFromDb = productService.findProductById(productId);
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
	}

}
