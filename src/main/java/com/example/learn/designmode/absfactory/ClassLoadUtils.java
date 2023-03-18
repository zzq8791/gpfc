package com.example.learn.designmode.absfactory;

import java.util.ArrayList;
import java.util.List;


public class ClassLoadUtils {

	public static Class<?>[] getArg(Object args[]){
		Class<?>[] paramtype = new Class[args.length];
		for(int i = 0 ;i < args.length;i++){
			if(args[i] instanceof ArrayList){
				paramtype[i] = List.class;
				continue;
			}
			if(args[i] instanceof String){
				paramtype[i] = String.class;
				continue;
			}
			paramtype[i] = args[i].getClass();
		}
		return paramtype;
	}
	
}
