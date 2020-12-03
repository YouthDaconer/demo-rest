package co.edu.usbcali.demo.service;

import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Integer> {

	public ShoppingCart getCurrentUserCart(String email);

}
