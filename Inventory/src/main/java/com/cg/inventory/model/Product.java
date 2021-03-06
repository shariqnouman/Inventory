package com.cg.inventory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is an entity class for products with getters, setters, and constructors
 * 
 * @author sharique nooman
 *
 */
@Entity(name = "products")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "product_name", nullable = false)
	private String productName;

	@NotBlank
	@Column(name = "product_description", nullable = false)
	private String productDescription;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "product_photo", nullable = true)
	private byte[] productPhoto;

	@NotNull
	@Positive
	@Column(name = "product_quantity", nullable = false)
	private Long productQuantity;

	@NotNull
	@Positive
	@Column(name = "product_price", nullable = false)
	private Float productPrice;

	@Column(name = "product_tag", nullable = true)
	private String productTag;

	@Column(name = "product_size", nullable = true)
	private String productSize;

	@Column(name = "product_unit", nullable = true)
	private String productUnit;

	@Size(min = 3, max = 30)
	@Column(name = "product_location", nullable = true)
	private String productLocation;

	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private List<Order> orders;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product() {
	}

	// DELETE this after testing
//	public Product(Long productId, String productName) {
//		super();
//		this.productId = productId;
//		this.productName = productName;
//	}

	public Product(String productName, String productDescription, Long productQuantity, Float productPrice,
			Category category) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductTag() {
		return productTag;
	}

	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductLocation() {
		return productLocation;
	}

	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
