package com.international.authoriziation.server.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
 import java.util.stream.Collectors;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
    		HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
	    
	    return message;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	  public ErrorMessage UserAlreadyExistException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
    		HttpStatus.CONFLICT.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
	    
	    return message;
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorMessage invalidTokenException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
    		HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
	    
	    return message;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }

	 

	 
	 @ExceptionHandler(TokenRefreshException.class)
	 @ResponseStatus(value = HttpStatus.FORBIDDEN)
	 	public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		 	ErrorMessage message = new ErrorMessage(
		 			HttpStatus.FORBIDDEN.value(),
		 			new Date(),
		 			ex.getMessage(),
		 			request.getDescription(false));
		 
		 	return message;
	  	} 
	 
	 	
	 
	 
	 @Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDate.now());
	        body.put("status", status.value());
	
	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());
	
	        body.put("errors", errors);
	
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    	}
	}
	

	 
