package com.example.learn.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.alibaba.fastjson.JSONObject;
import com.example.learn.core.filter.XHttpInputMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ControllerAdvice
public class MyRequestBodyAdvice extends RequestBodyAdviceAdapter {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		log.info("resquestBody : {}",methodParameter.hasParameterAnnotation(RequestBody.class));
		return true;
	}
	
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
			throws IOException {
		Class<?> paramObjClass = parameter.getParameterType();
		XHttpInputMessage message = new XHttpInputMessage(inputMessage, "UTF-8");
		byte[] body = IOUtils.toByteArray(message.getBody());
		String str = new String(body);
		JSONObject jsobj = JSONObject.parseObject(str);
		Map<String,Object> map = JSONObject.parseObject(str, Map.class);
		
		Object obj = jsobj.get("bizContent");
		log.info("obj : {}", obj);
		/*log.info("getParameterType : {}",  parameter.getParameterType());
		Object arg = BeanUtil.toBean(str, paramObjClass);
		log.info("jsobj : {}", jsobj);
		log.info("arg : {}", arg);*/
		
	//	HttpInputMessage rawInputStream = new ByteArrayInputStream(arg.toString().getBytes());
		InputStream rawInputStream = new ByteArrayInputStream(obj.toString().getBytes());
	        return new HttpInputMessage() {
	            @Override
	            public HttpHeaders getHeaders() {
	                return inputMessage.getHeaders();
	            }

	            @Override
	            public InputStream getBody() throws IOException {
	                return rawInputStream;
	            }
	        };
	}

	
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

}
