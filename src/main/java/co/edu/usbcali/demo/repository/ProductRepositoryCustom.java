package co.edu.usbcali.demo.repository;

import java.util.List;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepositoryCustom {
	
	public List<Product> getProductosMenoresAPrecio(Integer price);
	
}
