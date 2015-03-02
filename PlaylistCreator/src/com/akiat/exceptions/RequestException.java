package com.akiat.exceptions;

public class RequestException extends RuntimeException {

	public RequestException() {}  
	/** 
	* @param message A message to detail the exception.
	*/  
	public RequestException(String message) {  
		super(message); 
	}  
	/** 
	* @param cause origin of the execption.
	*/  
	public RequestException(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* @param message A message to detail the exception.
	* @param cause origin of the execption.
	*/  
	public RequestException(String message, Throwable cause) {  
		super(message, cause); 
	} 
}
