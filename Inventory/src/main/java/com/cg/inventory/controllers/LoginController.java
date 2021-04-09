package com.cg.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Login;
import com.cg.inventory.repositories.LoginRepository;
import com.cg.inventory.services.LoginServiceImpl;
import com.cg.inventory.utils.UserValidate;

/**
 * The LoginController class provides restful services to client like GET, POST,
 * DELETE, and PUT
 * 
 * @author Usha Nanga  
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/api/v1/login")
public class LoginController {

	// creating varible
	private static Login data; // static data

	@Autowired
	private LoginServiceImpl loginService;

	@Autowired
	private LoginRepository loginRepository;

	@GetMapping("/Login1/{userId}/{password}")
	public ResponseEntity<String> validateUser(@PathVariable(value = "userId") String userId,
			@PathVariable(value = "password") String password) throws ResourceNotFoundException {

		UserValidate validate = new UserValidate();
		boolean userValidate = validate.validateUser(userId); // validating userId;
		boolean passwordValidate = validate.validatePassword(password, userId); // validating password
		if (userValidate) {
			if (passwordValidate) {
				data = loginService.findByID(userId);
				System.out.println(data);
				// .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this
				// id :: " + userId));
				if (data != null) {
					if (data.getPassword().equals(password) && data.getUserId().equals(userId)
							&& data.getType().equals("Admin")) {

						return ResponseEntity.ok().body("sucess login");
					} else if (data.getPassword().equals(password) && data.getUserId().equals(userId)
							&& data.getType().equals("customer")) {

						return ResponseEntity.ok().body("sucess login");
					}
				} else {
					throw new ResourceNotFoundException("person not found for this id :: " + userId);
				}

				return ResponseEntity.ok().body("invalid login");
			} else {
				if (password.substring(0, 6).equals(userId.subSequence(0, 6))) {
					return ResponseEntity.ok().body("invalid user Id do not match with userName ");
				} else
					return ResponseEntity.ok().body("invalid Password Must contain atleast one special character");
			}
		} else {

			return ResponseEntity.ok().body("invalid userId Enter Valid userId 7 ");
		}
	}

	@GetMapping("/get")
	public List<Login> get() {
		return loginRepository.findAll();
	}

	// registration

	@PostMapping("/register")
	public Login createdata(@RequestBody Login data) throws ResourceNotFoundException {
		UserValidate vali = new UserValidate();
		Login data1 = new Login();
		boolean valid = vali.validateUser(data.getUserId());
		boolean valid1 = vali.validatePassword(data.getPassword(), data.getUserId());
		if (data.getUserId().equals("") || data.getPassword().equals("") || data.getEmailId().equals("")) {
			throw new ResourceNotFoundException("Enter valid data");
		}
		if (valid) {
			if (valid1) {
				data1 = loginService.createdata(data);
				return data1;
			} else {
				throw new ResourceNotFoundException("Enter valid Password ");
			}
		} else {
			throw new ResourceNotFoundException("Enter valid UserId ");
		}

	}

	@PutMapping("/update")
	public Login update(@RequestBody Login data) throws ResourceNotFoundException {
		UserValidate vali = new UserValidate();
		Login data1 = new Login();
		boolean valid = vali.validateUser(data.getUserId());
		boolean valid1 = vali.validatePassword(data.getPassword(), data.getUserId());
		if (data.getUserId().equals("") || data.getPassword().equals("") || data.getEmailId().equals("")) {
			throw new ResourceNotFoundException("Enter valid data");
		}
		if (valid) {
			if (valid1) {
				data1 = loginService.update(data);
				return data1;
			} else {
				throw new ResourceNotFoundException("Enter valid Password ");
			}
		} else {
			throw new ResourceNotFoundException("Enter valid UserId ");
		}

	}
}
