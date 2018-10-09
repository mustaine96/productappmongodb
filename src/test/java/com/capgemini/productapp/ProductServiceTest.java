package com.capgemini.productapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	Product product;
	
	@Mock
	private ProductRepository pdRepo;
	@InjectMocks
	private ProductServiceImpl peService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		product=new Product(1,"Name","category",250);

		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(pdRepo).build();
	}
	
	@Test
	public void testAddProduct() {
		when(pdRepo.save(Mockito.isA(Product.class))).thenReturn(product);
		assertEquals(product, peService.addProduct(product));
	}
	@Test
	public void testUpdateProduct() {
		
		Product product1=new Product(1,"BN","category",300);
		when(pdRepo.save(Mockito.isA(Product.class))).thenReturn(product1);
		assertEquals(product1,peService.updateProduct(product));
	}
	@Test
	public void testFindProductById() {
		Optional<Product> optionalProduct = Optional.of(product);
		when(pdRepo.findById(Mockito.isA(Integer.class))).thenReturn(optionalProduct);
	}
}
