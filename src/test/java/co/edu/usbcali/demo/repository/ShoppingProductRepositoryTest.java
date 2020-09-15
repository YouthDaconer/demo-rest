package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductRepositoryTest {

	private static Integer shprId = null;

	private static String proId = "APPL45"; // "APPL90"

	private static Integer carId = 1;

	private static Long total = 4500000L; // 4440000L

	@Autowired
	ShoppingProductRepository shoppingProductRepository;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingProduct shoppingProduct = new ShoppingProduct();
		shoppingProduct.setShprId(null);
		shoppingProduct.setQuantity(1);
		shoppingProduct.setTotal(total);

		Optional<Product> productOptional = productRepository.findById(proId);

		assertTrue(productOptional.isPresent(), "El producto con proId " + proId + "no existe");

		Product product = productOptional.get();
		shoppingProduct.setProduct(product);

		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(), "El shopping cart con carId " + carId + "no existe");

		ShoppingCart shoppingCart = shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);

		shoppingProduct = shoppingProductRepository.save(shoppingProduct);

		shprId = shoppingProduct.getShprId();

		assertNotNull(shprId, "El shprId es nulo");
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),
				"El shoppingProductOptional con shprId " + shprId + " no existe");
	}

	@Test
	@Transactional
	@Order(3)
	void update() {
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),
				"El shoppingProductOptional con shprId " + shprId + " no existe");

		ShoppingProduct shoppingProduct = shoppingProductOptional.get();
		shoppingProduct.setQuantity(2);

		shoppingProductRepository.save(shoppingProduct);
	}

	@Test
	@Transactional
	@Order(4)
	void delete() {
		Optional<ShoppingProduct> shoppingCartOptional = shoppingProductRepository.findById(shprId);
		assertTrue(shoppingCartOptional.isPresent(), "El shoppingCartOptional con carId " + shprId + " no existe");

		ShoppingProduct shoppingCart = shoppingCartOptional.get();
		shoppingProductRepository.delete(shoppingCart);
	}

}
