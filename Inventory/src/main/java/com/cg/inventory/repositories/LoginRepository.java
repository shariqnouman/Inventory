package com.cg.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.inventory.model.Login;

/**
 * This is an interface which defines CRUD methods for Login JPA repository
 * 
 * @author usha Nanga
 *
 */
public interface LoginRepository extends JpaRepository<Login, String> {

	/**
	 * This method for checking whether userId present in database or not
	 */
	@Query(value = "select t from login t where t.userId=:userId")
	Login findByID(@Param("userId") String userId);

}
