package com.capgemini.productapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductappApplicationTests {

	Product product;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// product = new Product(123, "vipul", "fghj", 0);
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAddProduct() throws Exception {

		product = new Product(123, "asdf", "fghj", 0);
		String content = "{\n" + "  \"productId\": \"123\",\n" + "  \"productName\": \"asdf\",\n"
				+ "  \"productCategory\": \"fghj\",\n" + "  \"productPrice\": \"0\"\n" + "}";

		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(product);
		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").exists()).andDo(print());

	}

	@Test
	public void testUpdateProduct() throws Exception {

		Product product = new Product(123, "vipul", "fghj", 0);

		when(productService.findProductById(product.getProductId())).thenReturn(product);

		String content = "{\"productId\": 123, \"productName\": \"vipul\", \"productCategory\": \"adfr\", \"productPrice\": 12}";

		product.setProductCategory("adfr");
		product.setProductPrice(12);
		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(product);

		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productId").value(123))
				.andExpect(jsonPath("$.productName").value("vipul"))
				.andExpect(jsonPath("$.productCategory").value("adfr")).andExpect(jsonPath("$.productPrice").value(12));

		verify(productService).updateProduct(Mockito.isA(Product.class));

	}

	@Test
	public void testDeleteProduct() throws Exception {
		product = new Product(123, "asdf", "fghj", 0);
		when(productService.findProductById(123)).thenReturn(product);
	//	when(productService.deleteProduct(Mockito.isA(Product.class)));
		
		
		mockMvc.perform(delete("/products/123").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk());
		
		verify(productService).findProductById(123);

	}

}
