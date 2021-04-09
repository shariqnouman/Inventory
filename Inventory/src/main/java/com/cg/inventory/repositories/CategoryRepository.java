package com.cg.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.inventory.model.Category;

/**
 * This is an interface which defines CRUD methods for Category JPA repository
 * 
 * @author sharique nooman
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
