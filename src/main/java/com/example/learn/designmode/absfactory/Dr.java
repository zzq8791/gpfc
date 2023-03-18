package com.example.learn.designmode.absfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dr {

	private static Map<String,String> map = new ConcurrentHashMap<>();
	
	public void setExp(String key ,String value){
		map.put(key, value);
	}
	
	public String getKey(String key){
		return map.get(key);
	}
	
}
