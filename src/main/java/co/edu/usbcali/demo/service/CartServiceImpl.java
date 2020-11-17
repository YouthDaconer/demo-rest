package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@Service
@Scope("singleton")
public class CartServiceImpl implements CartService {

	@Autowired
	CustomerService customerService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingProductService shoppingProductService;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer = null;

		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}

		customer = customerOptional.get();

		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}

		ShoppingCart shoppingCart = new ShoppingCart(0, customer, null, 0, 0L, "Y", null);

		shoppingCart = shoppingCartService.save(shoppingCart);

		return shoppingCart;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {

		ShoppingCart shoppingCart = null;
		Product product = null;
		Long totalShoppingProduct = 0L;
		Long totalShoppingCart = 0L;
		Integer shprId = 0;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (proId == null || proId.isBlank() == true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}

		if (quantity == null || quantity <= 0) {
			throw new Exception("El quantity es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart no existe");
		}

		shoppingCart = shoppingCartService.findById(carId).get();

		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}

		if (productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}

		product = productService.findById(proId).get();

		if (product.getEnable().equals("N") == true) {
			throw new Exception("El product esta inhabilitado");
		}
		
		ShoppingProduct shoppingProduct = new ShoppingProduct();
		shoppingProduct.setProduct(product);
		shoppingProduct.setQuantity(quantity);
		shoppingProduct.setShoppingCart(shoppingCart);
		shoppingProduct.setShprId(0);
		totalShoppingProduct = Long.valueOf(product.getPrice() * quantity);
		shoppingProduct.setTotal(totalShoppingProduct);

		shoppingProduct = shoppingProductService.save(shoppingProduct);

		totalShoppingCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);

		shoppingCart.setTotal(totalShoppingCart);
		shoppingCartService.update(shoppingCart);

		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {

		ShoppingCart shoppingCart = null;
		ShoppingProduct shoppingProduct = null;
		Integer shprId = 0;
		Product product = null;
		Long totalShoppingProduct = 0L;
		Long totalShoppingCart = 0L;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (proId == null || proId.isBlank() == true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart no existe");
		}

		shoppingCart = shoppingCartService.findById(carId).get();

		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}

		if (productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}

		product = productService.findById(proId).get();

		if (product.getEnable().equals("N") == true) {
			throw new Exception("El product esta inhabilitado");
		}

		shprId = shoppingProductService.getShoppingProductByProductId(carId, proId);

		if (shprId == null || shprId == 0) {
			throw new Exception("El producto no existe en el carro de compras");
		}

		shoppingProduct = shoppingProductService.findById(shprId).get();

		if (shoppingProduct.getQuantity() > 1) {
			totalShoppingProduct = Long.valueOf(product.getPrice() * (shoppingProduct.getQuantity() - 1));
			shoppingProduct.setTotal(totalShoppingProduct);
			shoppingProduct.setQuantity(shoppingProduct.getQuantity() - 1);
			shoppingProductService.update(shoppingProduct);

		} else if (shoppingProduct.getQuantity() == 1) {
			shoppingProductService.delete(shoppingProduct);
		}

		totalShoppingCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);

		if (totalShoppingCart == null) {
			shoppingCart.setTotal(0L);
		} else {
			shoppingCart.setTotal(totalShoppingCart);
		}

		shoppingCartService.update(shoppingCart);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clearCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart = null;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart no existe");
		}

		shoppingCart = shoppingCartService.findById(carId).get();

		shoppingProductService.deleteShoppingProductByCartId(carId);

		shoppingCart.setTotal(0L);
		shoppingCartService.update(shoppingCart);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart = null;
		List<ShoppingProduct> shoppingProducts = null;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart no existe");
		}
		
		return shoppingProductService.findShoppingProductByShoppingCart(carId);
	}

}
