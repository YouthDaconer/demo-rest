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

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodServiceTest {
	
	private final static Logger log = LoggerFactory.getLogger(PaymentMethodServiceTest.class);

	private static Integer id = null;
	
	@Autowired
	PaymentMethodService paymentMethodService;

	@Test
	@Transactional
	@Order(1)
	void save() throws Exception {
		log.info("save");

		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setName("RappiPay");
		paymentMethod.setEnable("Y");

		paymentMethodService.save(paymentMethod);
		id = paymentMethod.getPayId();
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() throws Exception {
		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(id);
		log.info("Método de pago con nombre: " + paymentMethodOptional.get().getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() throws Exception {
		assertTrue(paymentMethodService.findById(id).isPresent());
		
		PaymentMethod paymentMethod = paymentMethodService.findById(id).get();
		paymentMethod.setEnable("N");
		
		paymentMethodService.update(paymentMethod);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() throws Exception {
		assertTrue(paymentMethodService.findById(id).isPresent());
		
		PaymentMethod paymentMethod = paymentMethodService.findById(id).get();
		
		paymentMethodService.delete(paymentMethod);
	}

}
