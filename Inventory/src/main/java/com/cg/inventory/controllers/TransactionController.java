package com.cg.inventory.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.inventory.model.Transaction;
import com.cg.inventory.services.TransactionServiceImpl;

/**
 * The TransactionController class provides restful services to client like GET,
 * POST, DELETE, and PUT
 * 
 * @author sharique nooman
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/api/v1/transaction")
public class TransactionController {

	@Autowired
	private TransactionServiceImpl transactionService;

	/**
	 * GET method to view all transactions
	 * 
	 * @return List of all transactions
	 */
	@GetMapping
	public List<Transaction> viewAllTransactions() {
		return transactionService.viewAllTransactions();
	}

	/**
	 * POST method to create a new transaction
	 * 
	 * @param transaction
	 * @return Transaction object
	 */
	@PostMapping
	public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}

	/**
	 * DELETE method to remove an transaction
	 * 
	 * @param transactionId
	 */
	@DeleteMapping("{transactionId}")
	public void deleteTransaction(@PathVariable @Positive Long transactionId) {
		transactionService.deleteTransaction(transactionId);
	}

	/**
	 * GET method to view transaction by Id
	 * 
	 * @param transactionId
	 * @return Transaction object
	 */
	@GetMapping("{transactionId}")
	public Transaction viewTransactionById(@PathVariable @Positive Long transactionId) {
		return transactionService.viewTransactionById(transactionId);
	}

	/**
	 * PUT method to update transaction
	 * 
	 * @param transactionId
	 * @param transaction
	 * @return Transaction object
	 */
	@PutMapping("{transactionId}")
	public Transaction updateTransaction(@PathVariable @Positive Long transactionId,
			@Valid @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transactionId, transaction);
	}
}
