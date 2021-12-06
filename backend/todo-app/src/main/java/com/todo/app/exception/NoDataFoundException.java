package com.todo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {
	
	public NoDataFoundException(String message) {
		super(message);
	}
	
	public NoDataFoundException(String message, Throwable t) {
		super(message, t);
	}
}
