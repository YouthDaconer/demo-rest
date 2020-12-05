package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ProductRepository extends JpaRepository<Product, String>, ProductRepositoryCustom {

	public List<Product> findByNameContains(String cadena);

	public List<Product> findByNameStartsWith(String cadena);
	
	public List<Product> findByEnable(String estado);
	
	@Query("SELECT SUM(p.price) FROM Product p")
	public Double sumatoriaPrecio();
	
	@Query("SELECT p FROM Product p WHERE p.enable = 'Y'")
	public List<Product> findAllEnable();
}