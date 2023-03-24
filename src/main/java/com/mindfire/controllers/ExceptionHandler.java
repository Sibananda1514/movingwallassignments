package com.mindfire.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindfire.exception.CustomException;
import com.mindfire.response.ApiResponce;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ApiResponce<Boolean>> handleCustomeEx(CustomException ex){
		
		ApiResponce<Boolean> apiResponse = new ApiResponce<>();
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setResult(false);
		
		return ResponseEntity.ok(apiResponse);
	}
	
}
