package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<ApiError> handleRecordNotFoundException() {

		ApiError error = new ApiError(400,
				"No Record Found With Given Employee Id in our Database.Please Enter Correct Id!", new Date());

		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}
}
