package com.mobile.de;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mobile.de.customer.Customer;
import com.mobile.de.customer.CustomerResource;
import com.mobile.de.customer.CustomerService;



@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerResource.class, secure = false)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	Customer mockCustomer =  new Customer(10001L,"Customer1", "lastname123", "mobile.de","test@gmail.com");
	@Test
	public void retrieveCustomerData() throws Exception {

//		// 
//		Mockito.when(
//				customerService.list());
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/customer/10001").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse());
//		String expected = "{}";
//
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
	}

	
	}