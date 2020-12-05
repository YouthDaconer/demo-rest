package co.edu.usbcali.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.repository.ProductRepository;
import co.edu.usbcali.demo.repository.ShoppingProductRepository;

@Service
@Scope("singleton")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ShoppingProductRepository shoppingProductRepository;

	@Autowired
	Validator validator;

	@Override
	public void validate(Product entity) throws Exception {
		if (entity == null) {
			throw new Exception("El product es null");
		}

		Set<ConstraintViolation<Product>> constraintViolation = validator.validate(entity);

		if (constraintViolation.isEmpty() == false) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAllEnable() {
		return productRepository.findAllEnable();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(String id) throws Exception {
		return productRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return productRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Product save(Product entity) throws Exception {
		validate(entity);

		// Si existe, lanza un error
		if (productRepository.existsById(entity.getProId())) {
			throw new Exception("El product con proId: " + entity.getProId() + " ya existe");
		}

		return productRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Product update(Product entity) throws Exception {
		validate(entity);

		// Si no existe, lanza el error
		if (!productRepository.existsById(entity.getProId())) {
			throw new Exception("El product con proId: " + entity.getProId() + " no existe");
		}

		return productRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Product entity) throws Exception {
		if (entity == null) {
			throw new Exception("El entity es null");
		}

		if (entity.getProId() == null || entity.getProId().isBlank() == true) {
			throw new Exception("El proId es obligatorio");
		}

		// Si no existe, lanza el error
		if (!productRepository.existsById(entity.getProId())) {
			throw new Exception("El product con proId: " + entity.getProId() + " no existe. No se puede borrar");
		}
		
		List<ShoppingProduct> shoppingProducts = shoppingProductRepository.getShoppingProductByProduct(entity.getProId());
		
		if (shoppingProducts != null
				&& shoppingProducts.isEmpty() == false) {
			throw new RuntimeException(
					"El product con proId: " + entity.getProId() + " tiene ShoppingProducts, no se puede borrar");
		}
		
		/*productRepository.findById(entity.getProId()).ifPresent(productRepository -> {
			if (productRepository.getShoppingProducts() != null
					&& productRepository.getShoppingProducts().isEmpty() == false) {
				throw new RuntimeException(
						"El product con proId: " + entity.getProId() + " tiene ShoppingProducts, no se puede borrar");
			}
		});*/

		productRepository.deleteById(entity.getProId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isBlank() == true) {
			throw new Exception("El proId es obligatorio");
		}

		if (productRepository.existsById(id)) {
			delete(productRepository.findById(id).get());
		} else {
			throw new Exception("El product con proId " + id + " no existe");
		}
	}

}
