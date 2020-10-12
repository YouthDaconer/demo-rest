package co.edu.usbcali.demo.dto;

public class ShoppingProductDTO {

	private Integer shprId;	
	private String proId;	
	private Integer carId;
	private Integer quantity;		
	private Integer total;

	public ShoppingProductDTO() {
		super();
	}

	public ShoppingProductDTO(Integer shprId, String proId, Integer carId, Integer quantity, Integer total) {
		super();
		this.shprId = shprId;
		this.proId = proId;
		this.carId = carId;
		this.quantity = quantity;
		this.total = total;
	}

	public Integer getShprId() {
		return shprId;
	}

	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
