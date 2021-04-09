package com.cg.inventory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Transaction;
import com.cg.inventory.repositories.TransactionRepository;

/**
 * The TransactionServiceImpl class provides access to JPA methods to create,
 * remove, view, and update transactions of customer
 * 
 * @author sharique nooman
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * This method returns list of all transactions
	 * 
	 * @return list of transactions
	 */
	@Override
	public List<Transaction> viewAllTransactions() {
		return transactionRepository.findAll();
	}

	/**
	 * This method takes transaction details and creates a new transaction for
	 * customer
	 * 
	 * @param transaction
	 * @return transaction entity details
	 */
	@Override
	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.saveAndFlush(transaction);
	}

	/**
	 * This method takes transaction Id and deletes the transaction
	 * 
	 * @param transactionId
	 */
	@Override
	public void deleteTransaction(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

	/**
	 * This method returns transactions by searching with specific transaction Id
	 * 
	 * @param transactionId
	 * @return transaction details if found or else throw exception
	 */
	@Override
	public Transaction viewTransactionById(Long transactionId) {
		return transactionRepository.findById(transactionId).orElseThrow(
				() -> new ResourceNotFoundException("Transaction not found for this Id : " + transactionId));
	}

	/**
	 * This method updates transaction details by searching with transaction Id
	 * 
	 * @param transactionId
	 * @param transaction
	 * @return transaction details if found or else throw exception
	 */
	@Override
	public Transaction updateTransaction(Long transactionId, Transaction transaction) {
		Transaction existingTransaction = transactionRepository.findById(transactionId).orElseThrow(
				() -> new ResourceNotFoundException("Transaction not found for this Id : " + transactionId));
		BeanUtils.copyProperties(transaction, existingTransaction, "transactionId");
		return transactionRepository.saveAndFlush(existingTransaction);
	}
}
