package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {
	
	private final static Logger log = LoggerFactory.getLogger(CustomerServiceTest.class);

	private final static String email = "carlos.andres.caicedo@hotmail.com";
	
	@Autowired
	CustomerService customerService;

	@Test
	@Transactional
	@Order(1)
	void save() throws Exception {
		log.info("save");

		Customer customer = new Customer();
		customer.setAddress("Calle 509 # 56-34");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Carlos Caicedo");
		customer.setPhone("3013611705");
		customer.setToken("Dabafec0a1082");

		customerService.save(customer);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() throws Exception {
		log.info("findById");

		Optional<Customer> customerOptional = customerService.findById(email);

		// Siga si es true
		assertTrue(customerOptional.isPresent(), "El customer no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() throws Exception {
		log.info("update");

		Optional<Customer> customerOptional = customerService.findById(email);

		// Siga si es true, osea si existe
		assertTrue(customerOptional.isPresent(), "El customer no existe");

		Customer customer = customerOptional.get();

		customer.setEnable("N");

		customerService.update(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() throws Exception {
		log.info("delete");

		Optional<Customer> customerOptional = customerService.findById(email);

		assertTrue(customerOptional.isPresent(), "El customer no existe");

		Customer customer = customerOptional.get();

		customerService.delete(customer);
	}

}
