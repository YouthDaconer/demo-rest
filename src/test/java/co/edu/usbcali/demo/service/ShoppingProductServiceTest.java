package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@Rollback(false)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ShoppingProduct.class);

	private static Integer shprId = null;

	private static String proId = "APPL90";

	private static Integer carId = 2;

	@Autowired
	ShoppingProductService shoppingProductService;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingCartService shoppingcartService;

	@Test
	@Order(1)
	void save() throws Exception {
		Integer cantidad = 2;

		ShoppingProduct shoppingProduct = new ShoppingProduct();
		shoppingProduct.setQuantity(cantidad);

		Optional<ShoppingCart> shoppingCartOptional = shoppingcartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(), "El shoppingCart no existe");
		ShoppingCart shoppingCart = shoppingCartOptional.get();

		Optional<Product> productOptional = productService.findById(proId);
		assertTrue(productOptional.isPresent(), "El product no existe");
		Product product = productOptional.get();

		// Se setean los valores
		shoppingProduct.setProduct(product);
		shoppingProduct.setTotal(Long.valueOf(product.getPrice() * cantidad));
		shoppingProduct.setShoppingCart(shoppingCart);
		shoppingProduct = shoppingProductService.save(shoppingProduct);

		shprId = shoppingProduct.getShprId();
	}

	@Test
	@Order(2)
	void findById() throws Exception {

		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductService.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(), "El shoppingProduct no existe");
		ShoppingProduct shoppingproduct = shoppingProductOptional.get();

		log.info("Cantidad: " + shoppingproduct.getQuantity() + " y Total: " + shoppingproduct.getTotal());
	}

	@Test
	@Order(3)
	void update() throws Exception {
		Optional<ShoppingProduct> shoppingProductOptional = shoppingProductService.findById(shprId);

		ShoppingProduct shoppingProduct = shoppingProductOptional.get();
		shoppingProduct.setQuantity(4);

		shoppingProductService.update(shoppingProduct);
	}

	@Test
	@Order(4)
	void delete() throws Exception {
		Optional<ShoppingProduct> shopproductOptional = shoppingProductService.findById(shprId);

		ShoppingProduct shoppingproduct = shopproductOptional.get();

		shoppingProductService.delete(shoppingproduct);
	}

	@Test
	@Order(5)
	void count() throws Exception {
		log.info("Count: " + shoppingProductService.count());
	}

}
