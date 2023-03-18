package com.example.learn.designmode.absfactory;

import java.lang.reflect.Proxy;

public class JdkProxFactory {

	public static <T> T getProx(Class<T> cacheClazz ,Class<? extends ICacheAdapter>  icacheAdapter) throws InstantiationException, IllegalAccessException{
		InvokeHandle handle = new InvokeHandle(icacheAdapter.newInstance());
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return (T) Proxy.newProxyInstance(loader, new Class[]{cacheClazz}, handle);
	}
	
}
