package com.international.authoriziation.server.exception;

public class ResourceNotFoundException extends RuntimeException{
//	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
	    super();
	  }
	
	public ResourceNotFoundException(String msg) {
	    super(msg);
	  }
	
	public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
	}
}
