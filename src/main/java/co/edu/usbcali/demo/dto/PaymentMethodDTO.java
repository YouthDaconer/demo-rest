package co.edu.usbcali.demo.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PaymentMethodDTO {

	private String payId;
	
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String name;
	
	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
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
