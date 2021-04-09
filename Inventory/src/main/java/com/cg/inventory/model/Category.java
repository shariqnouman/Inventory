package com.cg.inventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name = "categories")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;

	@NotBlank
	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]+", message = "Please provide a valid name only alphabets and spaces")
	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@NotBlank
	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9 ]+")
	@Column(name = "category_description", nullable = false)
	private String categoryDescription;

	public Category() {
	}

	public Category(String categoryName, String categoryDescription) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
