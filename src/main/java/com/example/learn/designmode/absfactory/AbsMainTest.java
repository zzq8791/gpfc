package com.example.learn.designmode.absfactory;

public class AbsMainTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		CacheService dr = JdkProxFactory.getProx(CacheService.class, IrAdapter.class); 
		dr.set("dr", "drvalue");
		String strDr = dr.getKey("dr");
		System.out.println(strDr);
		
		CacheService red = JdkProxFactory.getProx(CacheService.class, RedisAdapter.class); 
		red.set("res", "resvalue");
		String strred = red.getKey("res");
		System.out.println(strred);
		
	}
	
}
