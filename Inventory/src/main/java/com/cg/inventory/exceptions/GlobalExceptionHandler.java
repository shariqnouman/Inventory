package com.cg.inventory.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * GlobalExceptionHandler class has exception handler methods for different
 * exceptions
 * 
 * @author sharique nooman
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Exception handler for ResourceNotFoundException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Exception handler for NumberFormatException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> numberFormatException(NumberFormatException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid input, Id should be a number",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Exception handler for InvalidFormatException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Object> invalidFormatException(InvalidFormatException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid input, Id should be a number",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
