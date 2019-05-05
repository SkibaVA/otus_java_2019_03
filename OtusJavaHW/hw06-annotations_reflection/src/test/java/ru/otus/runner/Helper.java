package ru.otus.runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ru.otus.Annotations.Annotations;

class Helper {
	public static Object сreateObject(Constructor<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		c.setAccessible(true);
		Object o = c.newInstance();
		c.setAccessible(false);
		
		return o;
	}
	
	public static void executeAsAccessible(Method m, Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		m.setAccessible(true);
		m.invoke(o);
		m.setAccessible(false);
	}
	
	public static void executeByKey(TestingContext context, Annotations key, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		if (context.getMethodMap().containsKey(key)) 
    	{
    		for(Method m: context.getMethodMap().get(key)) 
    		{
    			executeAsAccessible(m, obj);
    		}
    	}
	}
}
