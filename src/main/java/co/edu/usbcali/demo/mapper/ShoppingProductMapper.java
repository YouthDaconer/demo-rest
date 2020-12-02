package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;

@Mapper
public interface ShoppingProductMapper {
	@Mapping(source="product.proId", target="proId")
	
	@Mapping(source="product.name", target="name")
	
	@Mapping(source="product.image", target="image")
	
	@Mapping(source="product.price", target="price")
	
	@Mapping(source="product.detail", target="detail")
	
	@Mapping(source="shoppingCart.carId", target="carId")
	
	public ShoppingProductDTO toShoppingProductDTO(ShoppingProduct shoppingProduct);
	
	public ShoppingProduct toShoppingProduct(ShoppingProductDTO shoppingProductDTO);
	
	public List<ShoppingProductDTO> toShoppingProductsDTO(List<ShoppingProduct> shoppingProducts);
	
	public List<ShoppingProduct> toShoppingProducts(List<ShoppingProductDTO> shoppingProductDTOs);
}
