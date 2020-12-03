package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

	private final static Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@Autowired
	ShoppingProductMapper shoppingProductMapper;

	@PostMapping("/createCart")
	public ResponseEntity<?> createCart(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
		if (shoppingCartDTO.getEmail() == null) {
			throw new Exception("El email del cliente para el carrito de compras es nulo");
		}

		ShoppingCart shoppingCart = cartService.createCart(shoppingCartDTO.getEmail());
		shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartDTO);

	}

	@GetMapping("/existProductInCart/{carId}/{proId}")
	public ResponseEntity<?> existProductInCart(@PathVariable("carId") Integer carId,
			@PathVariable("proId") String proId) throws Exception {
		ShoppingProduct shoppingProduct = cartService.existProductInCart(carId, proId);
		ShoppingProductDTO shoppingProductDTO = shoppingProductMapper.toShoppingProductDTO(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductDTO);
	}

	@GetMapping("/getCurrentUserCart/{email}")
	public ResponseEntity<?> getCurrentUserCart(@PathVariable("email") String email) throws Exception {
		ShoppingCart shoppingCart = cartService.getCurrentUserCart(email);
		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartDTO);
	}

	@PostMapping("/addProduct/{carId}/{proId}/{quantity}")
	public ResponseEntity<?> addProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId,
			@PathVariable("quantity") Integer quantity) throws Exception {

		ShoppingProduct shoppingProduct = cartService.addProduct(carId, proId, quantity);
		ShoppingProductDTO shoppingProductDTO = shoppingProductMapper.toShoppingProductDTO(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductDTO);
	}

	@PostMapping("/removeProduct/{carId}/{proId}")
	public ResponseEntity<?> removeProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId)
			throws Exception {

		cartService.removeProduct(carId, proId);

		return ResponseEntity.ok().body(true);
	}

	@GetMapping("/findShoppingProductByShoppingCart/{carId}")
	public ResponseEntity<?> findAll(@PathVariable("carId") Integer carId) throws Exception {
		List<ShoppingProduct> shoppingProducts = cartService.findShoppingProductByShoppingCart(carId);
		List<ShoppingProductDTO> shoppingProductDTOs = shoppingProductMapper.toShoppingProductsDTO(shoppingProducts);

		return ResponseEntity.ok().body(shoppingProductDTOs);
	}

}
