package com.erpm.employeeManagementService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;

@RestControllerAdvice
public class SeniorityAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(SeniorityNotFoundException.class)
	public String seniorityNotFound(SeniorityNotFoundException ex) {
		String message = ex.getMessage();
		return message;
	}
}
