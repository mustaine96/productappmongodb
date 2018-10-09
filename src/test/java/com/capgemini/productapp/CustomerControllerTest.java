package com.capgemini.productapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.CustomerController;
import com.capgemini.productapp.entity.Customer;
import com.capgemini.productapp.service.CustomerService;
import com.capgemini.productapp.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {
	@Mock
	private CustomerServiceImpl service;
	@InjectMocks
	private CustomerController controller;

	private MockMvc mockMvc;
	private Customer customer;

	@Before
	public void setUp() {
		customer = new Customer(1, "Jimmi", "Hendrix", "@gmail.com", "unknown");
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAddCustomer() throws Exception {

		String content="{\r\n" + 
				"  \"customerId\": \"1\",\r\n" + 
				"  \"customerName\": \"Jimmi\",\r\n" + 
				"  \"customerPassword\": \"Hendrix\",\r\n" + 
				"  \"customerEmail\": \"@gmail.com\",\r\n" + 
				"  \"customerAddress\": \"unknown\"\r\n" + 
				"}";

		when(service.addCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
				.andExpect(status().isOk()).andExpect(status().isOk()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.customerName").value("Jimmi"));
		verify(service).addCustomer(Mockito.isA(Customer.class));
	
	}
	
	

}
