package com.example.learn.core.handle;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.learn.core.anno.CurrentUser;
import com.example.learn.core.filter.ContentCachingRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
public class MyHandlerMethodArgumentResolver extends  AbstractMessageConverterMethodArgumentResolver  {
	
	
	public MyHandlerMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
		log.info("converters: {}", converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		log.info("paramType : {},paramName : {}",parameter.getParameterType(),parameter.getParameterAnnotations());
		log.info("paramIndex : {}",parameter.getParameterIndex());
		log.info("parameter.hasParameterAnnotation(RequestBody.class) : {}",parameter.hasParameterAnnotation(RequestBody.class));
		return parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
	    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
	    ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
		String requestBody = new String(requestWrapper.getBody());
		Class<?> paramObjClass = parameter.getParameterType();
		JSONObject rbJson = JSONObject.parseObject(requestBody);
		log.info("rbJson : {}",rbJson);
		String bizContent = rbJson.getString("bizContent");
		log.info("bizContent : {}",bizContent);
    	Object arg = BeanUtil.toBean(bizContent, paramObjClass);
    	JSONObject.parseObject(bizContent, paramObjClass);
    	log.info("arg : {}",arg);
    	return JSONObject.parseObject(bizContent, paramObjClass);
	}

  /*  public MyHandlerMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
		// TODO Auto-generated constructor stub
	}*/

	


}
