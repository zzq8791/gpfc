package com.example.learn.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
//@Configuration
public class InitializingAdvice  implements InitializingBean {
	
	

	@Autowired
    private RequestMappingHandlerAdapter adapter;

    /**
     * 初始化ResponseBodyWrapFactoryBean对象之后，执行这个方法
     */
    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodArgumentResolver> argumentResolver = adapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> handlers = new ArrayList(argumentResolver);
        decorateHandlers(handlers);
        adapter.setArgumentResolvers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodArgumentResolver> handlers) {
        for(int i = 0;i<handlers.size();i++){
        	HandlerMethodArgumentResolver handler = handlers.get(i);
            if (handler instanceof RequestResponseBodyMethodProcessor) {
            	log.info("替换RequestResponseBodyMethodProcessor");
            	MyRequestResponseBodyMethodProcessor decorator = new MyRequestResponseBodyMethodProcessor(adapter.getMessageConverters());
                handlers.set(i, decorator);//用自定义的MyRequestResponseBodyMethodProcessor替换掉原来的RequestResponseBodyMethodProcessor类型处理器
                break;
            }
        }
    }

}
