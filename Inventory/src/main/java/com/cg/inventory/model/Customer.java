package com.cg.inventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is an entity class for customers with getters, setters, and constructors
 * 
 * @author sharique nooman
 *
 */
@Entity(name = "customers")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@NotBlank
	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]+", message = "Please provide a valid name only alphabets and spaces")
	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@NotBlank
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Please provide a valid mobile number of 10 digits")
	@Column(name = "customer_number", nullable = false)
	private String customerNumber;

	@NotBlank
	@Size(min = 5)
	@Column(name = "customer_address", nullable = false)
	private String customerAddress;

	@NotBlank
	@Email
	@Column(name = "customer_email", nullable = false)
	private String customerEmail;

	@NotBlank
	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9 ]+")
	@Column(name = "customer_store_name", nullable = false)
	private String customerStoreName;

//	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//	private List<Order> orders;

	public Customer() {
	}

	// DELETE after testing
//	public Customer(Long customerId, String customerName) {
//		super();
//		this.customerId = customerId;
//		this.customerName = customerName;
//	}

	public Customer(String customerName, String customerNumber, String customerAddress, String customerEmail,
			String customerStoreName) {
		super();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerStoreName = customerStoreName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerStoreName() {
		return customerStoreName;
	}

	public void setCustomerStoreName(String customerStoreName) {
		this.customerStoreName = customerStoreName;
	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

}
