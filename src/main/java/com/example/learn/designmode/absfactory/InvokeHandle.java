package com.example.learn.designmode.absfactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokeHandle implements InvocationHandler{

	private ICacheAdapter iCacheAdapter;
	
	
	public InvokeHandle(ICacheAdapter iCacheAdapter) {
		super();
		this.iCacheAdapter = iCacheAdapter;
	}




	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return iCacheAdapter.getClass().getMethod(method.getName(), ClassLoadUtils.getArg(args))
				.invoke(iCacheAdapter, args);
	}

}
