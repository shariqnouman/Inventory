package com.cg.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.inventory.model.Transaction;

/**
 * This is an interface which defines CRUD methods for transaction JPA
 * repository
 * 
 * @author sharique nooman
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
