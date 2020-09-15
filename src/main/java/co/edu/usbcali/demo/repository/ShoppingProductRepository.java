package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {

}