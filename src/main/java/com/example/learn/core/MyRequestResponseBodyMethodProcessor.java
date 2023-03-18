package com.example.learn.core;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.example.learn.core.anno.CurrentUser;

import java.util.List;

@Slf4j
public class MyRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

	public MyRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("getParameterType: {}", parameter.getParameterType());
		//log.info("parameter.getAnnotatedElement(): {}", parameter.getAnnotatedElement());
		log.info("parameter.getParameter(): {}", parameter.getParameter());
		log.info("parameter.hasParameterAnnotation(RequestBody.class): {}", parameter.hasParameterAnnotation(RequestBody.class));
		boolean resflag = parameter.hasParameterAnnotation(RequestBody.class);
		boolean currentflag = parameter.hasParameterAnnotation(CurrentUser.class);
		log.info("flag): {}",resflag);
		return resflag;
	}
}
