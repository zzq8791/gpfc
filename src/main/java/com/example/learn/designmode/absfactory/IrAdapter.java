package com.example.learn.designmode.absfactory;

public class IrAdapter  implements ICacheAdapter {

	Ir dd = new Ir();
	
	@Override
	public String getKey(String str) {
		// TODO Auto-generated method stub
		return dd.getKey(str);
	}

	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		dd.set(key, value);
	}

	
}
