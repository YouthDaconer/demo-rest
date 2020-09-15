package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(PaymentMethodRepositoryTest.class);

	private static Integer payId = null;

	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@Test
	@Transactional
	@Order(1)
	void save() {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("EFECTY");

		paymentMethod = paymentMethodRepository.save(paymentMethod);
		payId = paymentMethod.getPayId();
		assertNotNull(payId, "El PayId es nulo");

		log.info("PayId: " + payId);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod = paymentMethodRepository.findById(payId).get();

		assertNotNull(paymentMethod, "El paymentMethod no existe");
	}

	@Test
	@Transactional
	@Order(3)
	void update() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod = paymentMethodRepository.findById(payId).get();
		paymentMethod.setEnable("N");

		paymentMethodRepository.save(paymentMethod);
	}

	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod = paymentMethodRepository.findById(payId).get();
		paymentMethodRepository.delete(paymentMethod);
	}
}
