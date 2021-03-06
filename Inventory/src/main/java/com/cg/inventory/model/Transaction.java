package com.cg.inventory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is an entity class for transactions with getters, setters, and
 * constructors
 * 
 * @author sharique nooman
 *
 */
@Entity(name = "transactions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@NotNull
	@FutureOrPresent
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "transaction_date", nullable = false)
	private LocalDateTime transactionDate;

	@NotNull
	@Positive
	@Column(name = "total_price", nullable = false)
	private Float totalPrice;

	@NotNull
	@Positive
	@Column(name = "gst", nullable = false)
	private Float gst;

	@NotBlank
	@Column(name = "payment_type", nullable = false)
	private String paymentType;

	@NotNull
	@Positive
	@Column(name = "paid_amount", nullable = false)
	private Float paidAmount;

	@NotNull
	@PositiveOrZero
	@Column(name = "due_amount", nullable = false)
	private Float dueAmount;

	@OneToOne
	@JoinColumn(name = "order_id", nullable = false, unique = true)
	private Order order;

	public Transaction() {
	}

	public Transaction(LocalDateTime transactionDate, Float totalPrice, Float gst, String paymentType, Float paidAmount,
			Float dueAmount, Order order) {
		super();
		this.transactionDate = transactionDate;
		this.totalPrice = totalPrice;
		this.gst = gst;
		this.paymentType = paymentType;
		this.paidAmount = paidAmount;
		this.dueAmount = dueAmount;
		this.order = order;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Float getGst() {
		return gst;
	}

	public void setGst(Float gst) {
		this.gst = gst;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Float getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Float dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
