package co.edu.usbcali.demo.dto;

public class PaymentMethodDTO {

	private String payId;
	private String name;
	private String enable;

	public PaymentMethodDTO() {
		super();
	}

	public PaymentMethodDTO(String payId, String name, String enable) {
		super();
		this.payId = payId;
		this.name = name;
		this.enable = enable;
	}

	public String getProId() {
		return payId;
	}

	public void setProId(String payId) {
		this.payId = payId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
