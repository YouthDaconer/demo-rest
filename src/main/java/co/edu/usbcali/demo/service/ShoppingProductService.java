package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingProduct;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {
	
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	public Integer getShoppingProductByProductId (Integer carId, String proId);
	
	public void deleteShoppingProductByCartId(Integer carId);
	
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId);
	
}
