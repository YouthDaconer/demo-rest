package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {
	
	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	@Query("SELECT shpr.shprId FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId AND shpr.product.proId=:proId")
	public Integer getShoppingProductByProductId(Integer carId, String proId);
	
	@Query("DELETE FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	@Modifying
	public void deleteShoppingProductByCartId(Integer carId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId);

}
