package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Integer> {

	public ShoppingCart getCurrentUserCart(String email);

	public List<ShoppingCart> findByEmail(String email);
}
