package com.example.learn.core.custom.expclass;


import com.example.learn.core.custom.inf.IResponseEnum;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IResponseEnum responseEnum;
	

	public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
		super(message);
		this.responseEnum = responseEnum;
	}
	
	public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(message, cause);
		this.responseEnum = responseEnum;
	}
	
	public BaseException(IResponseEnum responseEnum) {
		super(responseEnum.getMessage());
		this.responseEnum = responseEnum;
	}
}
