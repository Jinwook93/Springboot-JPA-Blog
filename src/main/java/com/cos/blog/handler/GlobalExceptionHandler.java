package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//@ControllerAdvice 	//어디에서 exception이 발생하면 이 쪽으로 오게 하라 
@RestController
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=IllegalArgumentException.class)		//illegalArgumentException에 관한 예외일 경우
	//@ExceptionHandler(value=EmptyResultDataAccessException.class)
	//@ExceptionHandler(value=Exception.class)		모든 예외에 대한 handler
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
