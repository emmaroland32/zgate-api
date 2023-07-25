package com.eradiuxtech.customerservice;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class CustomerServiceApplicationTests {

//	@Container
//	@ServiceConnection
//	@ClassRule
//	public static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql")
//			.withDatabaseName("ihealth-customer")
//			.withUsername("root")
//			.withPassword("password@123");
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//
//	@Autowired
//	private CustomerService customerService;
//
//
//
//
//	@Test
//	void testMySQLContainerIsRunning() {
//		Assertions.assertTrue(mySQLContainer.isRunning());
//	}
//	@Test
//	void shouldCreateCustomer() throws Exception {
//		CreateCustomerRequest customerRequest = createCustomerRequest();
//
//		String customerRequestString = objectMapper.writeValueAsString(customerRequest);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer-service/customers/create")
//											  .contentType(MediaType.APPLICATION_JSON)
//											  .content(customerRequestString))
//			   .andExpect(status().isCreated());
//
//		Assertions.assertEquals("Customer Created Successfully, Awaiting Review", customerService.createCustomer(customerRequest));
//
//	}
//
//	private CreateCustomerRequest createCustomerRequest() {
//		return CreateCustomerRequest.builder()
//									.firstName("Segun")
//									.lastName("Iyanda")
//									.username("siyanda").email("email1@gmail.com")
//									.build();
//	}


}
