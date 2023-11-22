package com.erpm.employeeManagementService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;

@RestControllerAdvice
public class DepartmentAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DepartmentNotFoundException.class)
	public String departmentNotFound(DepartmentNotFoundException ex) {
		String message = ex.getMessage();
		return message;
	}
}
