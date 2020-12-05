package co.edu.usbcali.demo.dto;

public class ShoppingCartDTO {

	private Integer carId;
	private Integer total;
	private Integer items;
	private String email;
	private String name;
	private String address;
	private String payName;
	private Integer payId;
	private String enable;

	public ShoppingCartDTO() {
		super();
	}

	public ShoppingCartDTO(Integer carId, Integer total, Integer items, String email, String name, String address,
			String payName, Integer payId, String enable) {
		super();
		this.carId = carId;
		this.total = total;
		this.items = items;
		this.email = email;
		this.name = name;
		this.address = address;
		this.payName = payName;
		this.payId = payId;
		this.enable = enable;
	}



	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

}
