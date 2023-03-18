/*package com.example.learn.core.filter;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

@Component
public class MyConvertMessage extends AbstractMessageConverterMethodArgumentResolver{


	public MyConvertMessage(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		boolean resFlag = parameter.hasMethodAnnotation(ResponseBody.class);
		boolean reqFlag = parameter.hasParameterAnnotation(RequestBody.class);
		return reqFlag;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		
		Class<?>  classType = parameter.getParameterType();
		System.out.println(classType);
		return null;
	}
	
	
	

}
*/