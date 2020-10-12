package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;

@Mapper
public interface ShoppingProductMapper {
	
	public ShoppingProductDTO toShoppingProductDTO(ShoppingProduct shoppingProduct);
	
	public ShoppingProduct toShoppingProduct(ShoppingProductDTO shoppingProductDTO);
	
	public List<ShoppingProductDTO> toShoppingProductsDTO(List<ShoppingProduct> shoppingProducts);
	
	public List<ShoppingProduct> toShoppingProducts(List<ShoppingProductDTO> shoppingProductDTOs);
}
