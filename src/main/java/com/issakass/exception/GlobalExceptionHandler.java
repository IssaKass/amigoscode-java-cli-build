package com.issakass.exception;

import com.issakass.car.CarNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: abdallah-issakass
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CarNotAvailableException.class)
	public ResponseEntity<ProblemDetail> handle(CarNotAvailableException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
				HttpStatus.NOT_FOUND,
				e.getMessage()
		);
		return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
	}
}
