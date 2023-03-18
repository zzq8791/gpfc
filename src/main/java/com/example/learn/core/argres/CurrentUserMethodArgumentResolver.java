package com.example.learn.core.argres;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.example.learn.core.anno.CurrentUser;
import lombok.extern.slf4j.Slf4j;


/**
 * @CurrentUser 注解 解析器
 */
@Slf4j
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	  public CurrentUserMethodArgumentResolver() {
	    }
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
    	log.info("paramIndex : {}",parameter.getParameterIndex());
    	log.info("parameter.hasParameterAnnotation(CurrentUser.class) : {}",parameter.hasParameterAnnotation(CurrentUser.class) );
    	log.info("parameter.getParameterAnnotations : {}",JSON.toJSONString(parameter.getParameterAnnotations() ));
	   if (parameter.hasParameterAnnotation(CurrentUser.class)) {
           return true;
       }
       return false;
    }
 
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    	log.info("CurrentUserMethodArgumentResolver : {}",parameter.getParameterType()  );
    	//写点儿伪代码...
      //1. //从请求头中获取sessionId
        String token = webRequest.getHeader("token");
        
      //2.  //根据token从通过自己的逻辑获取到用户信息（你可以通过Redis 获取）
        UserVo vo = new UserVo();
        vo.setAge("12");
        vo.setUserName("mike");
      //3. //记得最后返回拿到的这个 user 对象
        return vo;
    }
}
