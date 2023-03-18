package com.example.learn.core.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommonResponse extends BaseResponse {

	private Object data;
	
}
