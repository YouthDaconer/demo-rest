package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}