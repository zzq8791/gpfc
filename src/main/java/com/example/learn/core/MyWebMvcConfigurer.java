package com.example.learn.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.learn.core.argres.CurrentUserMethodArgumentResolver;
import com.example.learn.core.handle.MyHandlerMethodArgumentResolver;
import com.example.learn.core.handle.TestHandle;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

//自定义拦截器
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	
	@Autowired
	private HttpMessageConverters httpMessageConverters;
	
	//拦截器
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将加一个拦截器，检查会话，所有/admin开头的请求都经过此拦截器
      //  registry.addInterceptor(new TestHandle()).addPathPatterns("/**");
    }*/
	
	/**
     * 允许访问的域
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 仅允许指定域名使用
      /*  if (!StringUtils.isBlank(allowCorsOrigin)) {
            String[] origin = allowCorsOrigin.split(",");
            for (String s : origin) {
                corsConfiguration.addAllowedOrigin(s);
            }
        }*/
        // 2允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//    	argumentResolvers.add(currentUserMethodArgumentResolver());
//    	argumentResolvers.add(myHandlerMethodArgumentResolver());
    }

    /**
     * 用户参数解析
     * @return CurrentUserMethodArgumentResolver 
     */
    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver ();
    }
    
//    @Bean
//    public MyHandlerMethodArgumentResolver myHandlerMethodArgumentResolver() {
//        return new MyHandlerMethodArgumentResolver (httpMessageConverters.getConverters());
//    }
	
}
