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
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingCart")
@CrossOrigin("*")
public class ShoppingCartController {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
		ShoppingCart shoppingCart = shoppingCartMapper.toShoppingCart(shoppingCartDTO);
		shoppingCart = shoppingCartService.save(shoppingCart);
		shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
		ShoppingCart shoppingCart = shoppingCartMapper.toShoppingCart(shoppingCartDTO);
		shoppingCartService.update(shoppingCart);
		shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartDTO);

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
		List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartMapper.toShoppingCartsDTO(shoppingCarts);

		return ResponseEntity.ok().body(shoppingCartDTOs);
	}

	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) throws Exception {
		List<ShoppingCart> shoppingCarts = shoppingCartService.findByEmail(email);
		List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartMapper.toShoppingCartsDTO(shoppingCarts);

		return ResponseEntity.ok().body(shoppingCartDTOs);
	}

	@GetMapping("/findById/{carId}")
	public ResponseEntity<?> findById(@PathVariable("carId") Integer carId) throws Exception {
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(carId);
		if (shoppingCartOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}
		ShoppingCart shoppingCart = shoppingCartOptional.get();

		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartDTO);
	}

	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<?> delete(@PathVariable("carId") Integer carId) throws Exception {
		shoppingCartService.deleteById(carId);

		return ResponseEntity.ok().build();
	}

}
