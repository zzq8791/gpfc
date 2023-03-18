package com.example.learn.designmode.absfactory;

public interface CacheService {

    String getKey(String str);
	
	void set(String key , String value);
	
	
}
