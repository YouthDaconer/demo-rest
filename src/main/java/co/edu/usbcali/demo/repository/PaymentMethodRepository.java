package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
	
	@Query("SELECT p FROM PaymentMethod p WHERE p.enable = 'Y'")
	public List<PaymentMethod> findAllEnable();

}