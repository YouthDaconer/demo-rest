package co.edu.usbcali.demo.jpql;

import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import co.edu.usbcali.demo.domain.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TestCustomerJPQL {

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCustomerJPQL.class);

	@Autowired
	EntityManager entityManager;

	@BeforeEach
	public void beforeEach() {
		log.info("beforeEach");
		assertNotNull(entityManager, "El enetityManager es nulo");
	}

	@Test
	public void selectAll() {
		log.info("selectAll");
		String jpql = "SELECT cus FROM Customer cus";
		List<Customer> customers = entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		for(Customer customer : customers) {
			log.info(customer.getEmail());
			log.info(customer.getName());
		}
	}
	
	@Test
	public void selectLike() {
		log.info("selectLike");
		String jpql = "SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'";
		List<Customer> customers = entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
	}
	
	@Test
	public void selectWhereEnable() {
		log.info("selectWhereEnable");
		String jpql = "SELECT cus FROM Customer cus WHERE cus.enable = 'Y'";
		List<Customer> customers = entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
	}
	
	@Test
	public void selectWhereParam() {
		log.info("selectWhereParam");
		String jpql = "SELECT cus FROM Customer cus WHERE cus.enable = :enable AND cus.email = :email";
		List<Customer> customers = entityManager.
				createQuery(jpql, Customer.class).
				setParameter("enable", "Y").
				setParameter("email", "pboolrh@so-net.ne.jp").
				getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
	}
}
