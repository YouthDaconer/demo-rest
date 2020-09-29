package co.edu.usbcali.demo.service;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.ShoppingCart;

@Rollback(false)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ShoppingCartServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceTest.class);

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	CustomerService customerService;

	@Autowired
	PaymentMethodService paymentMethodService;

	private static Integer carId = null;

	private static Integer payId = 1;

	private static String email = "abouskillmv@github.com";

	@Test
	@Order(1)
	void save() throws Exception {

		ShoppingCart shoppingcart = new ShoppingCart();
		shoppingcart.setCarId(null);
		shoppingcart.setItems(2);
		shoppingcart.setTotal(123456789L);
		shoppingcart.setEnable("Y");

		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);
		PaymentMethod paymentMethod = paymentMethodOptional.get();

		Optional<Customer> customerOptional = customerService.findById(email);
		Customer customer = customerOptional.get();
		shoppingcart.setCustomer(customer);

		shoppingcart.setPaymentMethod(paymentMethod);
		shoppingcart = shoppingCartService.save(shoppingcart);

		carId = shoppingcart.getCarId();
	}

	@Test
	@Order(2)
	void findbyid() throws Exception {

		Optional<ShoppingCart> shoppingcartoptional = shoppingCartService.findById(carId);
		log.info("Items: " + shoppingcartoptional.get().getItems() + " y Total: "
				+ shoppingcartoptional.get().getTotal());
	}

	@Test
	@Order(3)
	void update() throws Exception {
		Optional<ShoppingCart> shoppingcartoptional = shoppingCartService.findById(carId);

		ShoppingCart shoppingcart = shoppingcartoptional.get();
		shoppingcart.setEnable("N");

		shoppingCartService.update(shoppingcart);
	}

	@Test
	@Order(4)
	void delete() throws Exception {
		Optional<ShoppingCart> shoppingcartoptional = shoppingCartService.findById(carId);

		ShoppingCart shoppingcart = shoppingcartoptional.get();
		shoppingCartService.delete(shoppingcart);
	}

	@Test
	@Order(5)
	void count() {
		log.info("Número de shoppingCarts: " + shoppingCartService.count());
	}
}
