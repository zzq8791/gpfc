/*
package com.example.learn.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class TestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest requestWra = null;
		requestWra = new ContentCachingRequestWrapper(request);
		if(requestWra instanceof ContentCachingRequestWrapper){
			System.out.println("request 参数处理过滤器开始执行");
		}
	//	requestWra.setAttribute("VV", "123");
		// TODO Auto-generated method stub
		System.out.println("过滤器开始执行");
		filterChain.doFilter(request, response);
	}

}
*/
