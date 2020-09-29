package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.repository.ShoppingProductRepository;

@Service
@Scope("singleton")
public class ShoppingProductServiceImpl implements ShoppingProductService {

	@Autowired
	ShoppingProductRepository shoppingProductRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findAll() {

		return shoppingProductRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct save(ShoppingProduct entity) throws Exception {
		validate(entity);

		return shoppingProductRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct update(ShoppingProduct entity) throws Exception {
		validate(entity);

		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El shoppingProduct con id " + entity.getShprId() + " no existe");
		}

		return shoppingProductRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ShoppingProduct entity) throws Exception {

		if (entity == null) {
			throw new Exception("El shoppingProduct es nulo");
		}

		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El shoppingProduct con id " + entity.getShprId() + " no existe");
		}

		if (entity.getShprId() == null || entity.getShprId() < 1) {
			throw new Exception("El shoppingProduct con id está vacio");
		}

		shoppingProductRepository.deleteById(entity.getShprId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new Exception("El id es null");
		}

		if (shoppingProductRepository.existsById(id)) {
			delete(shoppingProductRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ShoppingProduct> findById(Integer id) throws Exception {
		return shoppingProductRepository.findById(id);
	}

	@Override
	public void validate(ShoppingProduct entity) throws Exception {

		if (entity == null) {
			throw new Exception("El shoppingProduct es nulo");
		}

		if (entity.getProduct() == null) {
			throw new Exception("El product es nulo");
		}

		if (entity.getShoppingCart() == null) {
			throw new Exception("El shoppingCart es nulo");
		}

		if (entity.getQuantity() == null || entity.getQuantity() == 0) {
			throw new Exception("La cantidad es nula");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return shoppingProductRepository.count();
	}

}
