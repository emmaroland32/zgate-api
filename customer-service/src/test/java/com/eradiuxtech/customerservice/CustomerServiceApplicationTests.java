package com.eradiuxtech.customerservice;

import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class CustomerServiceApplicationTests {

	@Container
	@ServiceConnection
	@ClassRule
	public static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql")
			.withDatabaseName("ihealth-customer")
			.withUsername("root")
			.withPassword("password@123");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Autowired
	private CustomerService customerService;




	@Test
	void testMySQLContainerIsRunning() {
		Assertions.assertTrue(mySQLContainer.isRunning());
	}
	@Test
	void shouldCreateCustomer() throws Exception {
		CreateCustomerRequest customerRequest = createCustomerRequest();

		String customerRequestString = objectMapper.writeValueAsString(customerRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer-service/customers/create")
											  .contentType(MediaType.APPLICATION_JSON)
											  .content(customerRequestString))
			   .andExpect(status().isCreated());

		Assertions.assertEquals("Customer Created Successfully, Awaiting Review", customerService.createCustomer(customerRequest));

	}

	private CreateCustomerRequest createCustomerRequest() {
		return CreateCustomerRequest.builder()
									.firstName("Segun")
									.lastName("Iyanda")
									.username("siyanda").email("email1@gmail.com")
									.build();
	}


}
