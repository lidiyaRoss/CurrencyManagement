package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String exceptionMessge){
		super(exceptionMessge);
		log.info(exceptionMessge);
	}
	
	

}
