package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer carId;
	
	private Customer customer;
	
	private PaymentMethod paymentMethod;
	
	private Integer items;

	private Long total;
	private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);

	public ShoppingCart() {
	}

	public ShoppingCart(Integer carId, Customer customer, Integer items, PaymentMethod paymentMethod,
			List<ShoppingProduct> shoppingProducts, Long total) {
		this.carId = carId;
		this.customer = customer;
		this.paymentMethod = paymentMethod;
		this.items = items;
		this.total = total;
		this.shoppingProducts = shoppingProducts;
	}

	@Id
	@Column(name = "car_id", unique = true, nullable = false)
	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_id")
	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "items", nullable = false)
	public Integer getItems() {
		return this.items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	@Column(name = "total", nullable = false)
	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
	public List<ShoppingProduct> getShoppingProducts() {
		return this.shoppingProducts;
	}

	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}
}
