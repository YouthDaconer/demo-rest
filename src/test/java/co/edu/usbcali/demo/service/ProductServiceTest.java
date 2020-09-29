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

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductServiceTest {
	
	private final static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	private final static String id = "MOTO1";
	
	@Autowired
	ProductService productService;

	@Test
	@Transactional
	@Order(1)
	void save() throws Exception {
		log.info("save");

		Product product = new Product();
		product.setProId(id);
		product.setName("Motorola G5S Plus");
		product.setPrice(750000);
		product.setDetail("Motorola G5S Plus");
		product.setImage(
				"https://www.xatakandroid.com/analisis/moto-g5s-plus-analisis-caracteristicas-precio-especificaciones");
		product.setEnable("Y");

		productService.save(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() throws Exception {
		log.info("findById");

		Optional<Product> productOptional = productService.findById(id);

		// Siga si es true
		assertTrue(productOptional.isPresent(), "El product no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() throws Exception {
		log.info("update");

		Optional<Product> productOptional = productService.findById(id);

		// Siga si es true, osea si existe
		assertTrue(productOptional.isPresent(), "El product no existe");

		Product product = productOptional.get();

		product.setEnable("N");

		productService.update(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() throws Exception {
		log.info("delete");

		Optional<Product> productOptional = productService.findById(id);

		assertTrue(productOptional.isPresent(), "El product no existe");

		Product product = productOptional.get();

		productService.delete(product);
	}

}
