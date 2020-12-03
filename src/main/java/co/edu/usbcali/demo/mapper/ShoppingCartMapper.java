package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;

@Mapper
public interface ShoppingCartMapper {
	@Mapping(source="shoppingCart.carId", target="carId")
	
	@Mapping(source="shoppingCart.total", target="total")
	
	@Mapping(source="shoppingCart.items", target="items")
	
	@Mapping(source="customer.email", target="email")
	
	@Mapping(source="paymentMethod.payId", target="payId")
	
	@Mapping(source="shoppingCart.enable", target="enable")
	
	public ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart);
	
	public ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);
	
	public List<ShoppingCartDTO> toShoppingCartsDTO(List<ShoppingCart> shoppingCarts);
	
	public List<ShoppingCart> toShoppingCarts(List<ShoppingCartDTO> shoppingCartDTOs);
}
