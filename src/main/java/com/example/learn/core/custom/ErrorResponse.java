package com.example.learn.core.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {

	private int code;
	private String message;
	
}
