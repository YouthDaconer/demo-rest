package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;

@Mapper
public interface ShoppingCartMapper {
	
	public ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart);
	
	public ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);
	
	public List<ShoppingCartDTO> toShoppingCartsDTO(List<ShoppingCart> shoppingCarts);
	
	public List<ShoppingCart> toShoppingCarts(List<ShoppingCartDTO> shoppingCartDTOs);
}
