package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class CartServiceTest {

	@Autowired
	CartService cartService;

	@Autowired
	ShoppingProductService shoppingProductService;

	@Test
	void debeCrearUnShoppingCart() throws Exception {
		// Arrange
		String email = "abaglowbn@furl.net";
		ShoppingCart shoppingCart = null;

		// Act
		shoppingCart = cartService.createCart(email);

		// Assert
		assertNotNull(shoppingCart);
	}

	@Test
	void noDebeCrearUnShoppingCartPorCustomerDisable() throws Exception {
		// Arrange
		String email = "abeamondqq@harvard.edu";

		// Act
		assertThrows(Exception.class, () -> cartService.createCart(email));

	}

	@Test
	void noDebeCrearUnShoppingCartPorCustomerNull() throws Exception {
		// Arrange
		String email = null;

		// Act
		assertThrows(Exception.class, () -> cartService.createCart(email));
	}

	@Test
	void noDebeCrearUnShoppingCartPorCustomerNoExiste() throws Exception {
		// Arrange
		String email = "jperez@vvv.com";

		// Act
		assertThrows(Exception.class, () -> cartService.createCart(email));
	}

	@Test
	void debeAgregarProductShoppingCart() throws Exception {
		// Arrange
		Integer carId = 4;
		String proId = "APPL45";
		Integer quantity = 2;
		ShoppingProduct shoppingProduct = null;

		// Act
		shoppingProduct = cartService.addProduct(carId, proId, quantity);

		// Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
	}

	@Test
	void debeEliminarProductShoppingCart() throws Exception {
		// Arrange
		Integer carId = 4;
		String proId = "APPL45";

		cartService.removeProduct(carId, proId);
	}
	
	@Test
	void debeVaciarShoppingCart() throws Exception {
		Integer carId = 4;
	
		cartService.clearCart(carId);
	}
	
	@Test
	void debeObtenerShoppingProductByShoppingCart() throws Exception {
		Integer carId = 4;
		List<ShoppingProduct> shoppingProducts = null;
		
		shoppingProducts = shoppingProductService.findShoppingProductByShoppingCart(carId);
	
		// Assert
		assertNotNull(shoppingProducts, "El shoppingCart no tiene shoppingProducts");
	}

}
