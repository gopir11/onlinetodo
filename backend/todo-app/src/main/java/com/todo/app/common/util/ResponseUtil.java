package com.todo.app.common.util;

import com.todo.app.common.response.MetaData;
import com.todo.app.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	private static final boolean SUCCESS = true;

	private ResponseUtil() {
		throw new UnsupportedOperationException();
	}

	public static <T> ResponseEntity<Response<T>> okResponse(T object) {
		return getResponseEntity(object, new MetaData(true, "Success"), HttpStatus.OK);
	}

	public static <T> ResponseEntity<Response<T>> okResponse(T object, boolean success, String description) {
		return getResponseEntity(object, new MetaData(success, description), HttpStatus.OK);
	}

	public static <T> ResponseEntity<Response<T>> createdResponse(T object) {
		return getResponseEntity(object, new MetaData(true, "Success"), HttpStatus.CREATED);
	}

	public static <T> ResponseEntity<Response<T>> createdResponse(T object, boolean success, String description) {
		return getResponseEntity(object, new MetaData(success, description), HttpStatus.CREATED);
	}

	public static <T> ResponseEntity<Response<T>> getResponseEntity(T object, MetaData metaData, HttpStatus httpStatus) {
		return new ResponseEntity<>(new Response<>(object, metaData), httpStatus);
	}

}