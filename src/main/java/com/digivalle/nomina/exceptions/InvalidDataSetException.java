package com.digivalle.nomina.exceptions;

public class InvalidDataSetException extends IllegalArgumentException {
 
	private static final long serialVersionUID = 6283441411293081284L;

	public InvalidDataSetException(String message){
		super(message);
	}
	
	public InvalidDataSetException(String message, Throwable e){
		super(message, e);
	}
}
