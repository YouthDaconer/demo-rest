package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.mapper.ProductMapper;
import co.edu.usbcali.demo.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
		try {
			Product product = productMapper.toProduct(productDTO);
			product = productRepository.save(product);
			productDTO = productMapper.toProductDTO(product);

			return ResponseEntity.ok().body(productDTO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			// Lista de Products
			List<Product> products = productRepository.findAll();
			// Declaro arreglo de DTOs
			List<ProductDTO> productDTOs = productMapper.toProductsDTO(products);

			return ResponseEntity.ok().body(productDTOs);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {

		try {

			Optional<Product> productOptional = productRepository.findById(id);

			if (productOptional.isPresent() == false) {
				return ResponseEntity.ok().body("Product Not Found");
			}

			Product product = productOptional.get();
			// Paso la información del Entity al DTO
			ProductDTO productDTO = productMapper.toProductDTO(product);

			return ResponseEntity.ok().body(productDTO);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
