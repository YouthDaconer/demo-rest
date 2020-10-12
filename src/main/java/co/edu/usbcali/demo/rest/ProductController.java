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

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.mapper.ProductMapper;
import co.edu.usbcali.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

	private final static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@Autowired
	ProductMapper productMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO) throws Exception {
		Product product = productMapper.toProduct(productDTO);
		product = productService.save(product);
		productDTO = productMapper.toProductDTO(product);

		return ResponseEntity.ok().body(productDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception {
		Product product = productMapper.toProduct(productDTO);
		productService.update(product);
		productDTO = productMapper.toProductDTO(product);

		return ResponseEntity.ok().body(productDTO);

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<Product> products = productService.findAll();
		List<ProductDTO> productDTOs = productMapper.toProductsDTO(products);

		return ResponseEntity.ok().body(productDTOs);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		Optional<Product> productOptional = productService.findById(id);
		if (productOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}
		Product product = productOptional.get();

		ProductDTO productDTO = productMapper.toProductDTO(product);

		return ResponseEntity.ok().body(productDTO);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) throws Exception {
		productService.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
