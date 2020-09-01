package co.edu.usbcali.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import co.edu.usbcali.demo.domain.Product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@Autowired
	@Lazy
	EntityManager em;

	public List<Product> getProductosMenoresAPrecio(Integer price) {
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.price < :price", Product.class);
		return query.setParameter("price", price).getResultList();
	}
	
}