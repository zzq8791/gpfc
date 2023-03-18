package com.example.learn.designmode.absfactory;

public interface ICacheAdapter {

	String getKey(String str);
	
	void set(String key , String value);
	
}
