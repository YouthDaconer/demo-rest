package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductTest {

	private final static String id = "MOTO1";
	
	private final static Logger log = LoggerFactory.getLogger(ProductTest.class);
	
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, id);
		
		assertNull(product, "El producto con id " + id + " ya está registrado");
	
		product = new Product();
		product.setProId(id);
		product.setName("Motorola G5S Plus");
		product.setPrice(750000);
		product.setDetail("Motorola G5S Plus");
		product.setImage("https://www.xatakandroid.com/analisis/moto-g5s-plus-analisis-caracteristicas-precio-especificaciones");
		product.setEnable("Y");
		
		entityManager.persist(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, id);
		
		assertNotNull(product, "El producto con id " + id +" no existe");

		log.info(product.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, id);
		
		assertNotNull(product, "El producto con id " + id + " no existe");

		product.setEnable("N");
		
		entityManager.merge(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, id);
		
		assertNotNull(product, "El producto con id " + id + " no existe");

		entityManager.remove(product);
	}
}
