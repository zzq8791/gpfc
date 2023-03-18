package com.example.learn.designmode.absfactory;

public class RedisAdapter implements ICacheAdapter {

	Dr dd = new Dr();
	
	@Override
	public String getKey(String str) {
		// TODO Auto-generated method stub
		return dd.getKey(str);
	}

	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		dd.setExp(key, value);
	}

}
