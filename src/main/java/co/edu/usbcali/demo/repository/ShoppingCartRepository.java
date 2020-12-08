package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	@Query("SELECT shcr FROM ShoppingCart shcr WHERE shcr.customer.email=:email AND shcr.paymentMethod IS NULL")
	public ShoppingCart getCurrentUserCart(String email);
	
	@Query("SELECT shcr FROM ShoppingCart shcr WHERE shcr.customer.email=:email")
	public List<ShoppingCart> findByEmail(String email);

}