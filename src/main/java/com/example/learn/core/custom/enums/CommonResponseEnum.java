package com.example.learn.core.custom.enums;

import com.example.learn.core.custom.inf.BusinessExceptionAssert;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CommonResponseEnum  implements BusinessExceptionAssert{

	 /**
     * Bad pat
     */
	SERVER_ERROR(8001, "Bad path.");   

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
	
}
