package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
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
class ProductRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);

	private final static String id = "MOTO1";

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");

		Optional<Product> productOptional = productRepository.findById(id);

		// Si es falso, no existe
		assertFalse(productOptional.isPresent(), "El producto ya existe");

		Product product = new Product();
		product.setProId(id);
		product.setName("Motorola G5S Plus");
		product.setPrice(750000);
		product.setDetail("Motorola G5S Plus");
		product.setImage(
				"https://www.xatakandroid.com/analisis/moto-g5s-plus-analisis-caracteristicas-precio-especificaciones");
		product.setEnable("Y");

		productRepository.save(product);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		log.info("findById");

		Optional<Product> productOptional = productRepository.findById(id);

		// Siga si es true
		assertTrue(productOptional.isPresent(), "El producto no existe");
	}

	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");

		Optional<Product> productOptional = productRepository.findById(id);

		// Siga si es true, osea si existe
		assertTrue(productOptional.isPresent(), "El producto no existe");

		Product product = productOptional.get();

		product.setEnable("N");

		productRepository.save(product);
	}

	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");

		Optional<Product> productOptional = productRepository.findById(id);

		assertTrue(productOptional.isPresent(), "El producto no existe");

		Product product = productOptional.get();

		productRepository.delete(product);
	}

	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			log.info("Nombre:" + product.getName());
			log.info("Precio:" + product.getPrice());
		}
	}

	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("Count:" + productRepository.count());
	}

	@Test
	@Transactional
	@Order(7)
	void getProductosMenoresAPrecio() {
		List<Product> products = productRepository.getProductosMenoresAPrecio(4499999);
		assertFalse(products.isEmpty());
		products.forEach(product -> {
			log.info("Nombre:" + product.getName());
			log.info("Precio:" + product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void getProductosLikeNombre() {
		List<Product> products = productRepository.findByNameContains("X");
		assertFalse(products.isEmpty());
		products.forEach(product -> {
			log.info("Nombre:" + product.getName());
			log.info("Precio:" + product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void getProductosComienzaNombre() {
		List<Product> products = productRepository.findByNameStartsWith("M");
		assertFalse(!products.isEmpty());
		products.forEach(product -> {
			log.info("Nombre:" + product.getName());
			log.info("Precio:" + product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(10)
	void getProductosPorEstado() {
		List<Product> products = productRepository.findByEnable("N");
		assertFalse(!products.isEmpty());
		products.forEach(product -> {
			log.info("Nombre:" + product.getName());
			log.info("Precio:" + product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(11)
	void getProductosSumatoriaPrecio() {
		Double sumatoriaPrecios = productRepository.sumatoriaPrecio();
		assertEquals(sumatoriaPrecios, sumatoriaPrecios);
		log.info("Sumatoria de precios:" + String.valueOf(sumatoriaPrecios));
	}

}
