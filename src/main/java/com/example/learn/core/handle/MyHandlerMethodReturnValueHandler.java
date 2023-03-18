package com.example.learn.core.handle;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

	public MyHandlerMethodReturnValueHandler(){
		
	}
	
	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		// TODO Auto-generated method stub
		log.info("returnType：{}",returnType.getMethod());
		return true;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		// TODO Auto-generated method stub
		log.info("returnValue：{}",returnValue);
		log.info("returnType：{}",returnType);
		log.info("mavContainer：{}",mavContainer);
	}

}
