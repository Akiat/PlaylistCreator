package com.akiat.exceptions;

public class AuthException extends RuntimeException {

	public AuthException() {}  
	/** 
	* @param message A message to detail the exception.
	*/  
	public AuthException(String message) {  
		super(message); 
	}  
	/** 
	* @param cause origin of the execption.
	*/  
	public AuthException(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* @param message A message to detail the exception.
	* @param cause origin of the execption.
	*/  
	public AuthException(String message, Throwable cause) {  
		super(message, cause); 
	} 
}
