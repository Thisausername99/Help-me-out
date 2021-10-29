/**
 * 
 */
package com.international.codyweb.exception;

/**
 * @author Cody Hoang
 *
 */
public class ResourceNotFoundException extends RuntimeException{
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
