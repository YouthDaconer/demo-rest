package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "customer", schema = "public")
public class Customer implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Email
	@Size(min = 3, max = 255)
	private String email;
	
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String address;

	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String enable;
	
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String name;
	
	@NotNull
	@Size(min = 6, max = 255)
	@NotEmpty
	private String phone;
	
	@Size(max = 255)
	private String token;
	
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);

	public Customer() {
	}

	public Customer(String email, String address, String enable, String name, String phone,
			List<ShoppingCart> shoppingCarts, String token) {
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
		this.shoppingCarts = shoppingCarts;
	}

	
	@Id
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "token", nullable = false)	
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
}
