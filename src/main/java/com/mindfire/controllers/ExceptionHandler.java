package com.mindfire.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindfire.exception.CustomException;
import com.mindfire.response.ApiResponse;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ApiResponse<Boolean>> handleCustomeEx(CustomException ex){
		
		ApiResponse<Boolean> apiResponse = new ApiResponse<>();
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setResult(false);
		
		return ResponseEntity.ok(apiResponse);
	}
	
}
