package ru.otus.runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import ru.otus.Annotations.*;

final public class Runner {
	
	private Runner() {
		throw new UnsupportedOperationException();
	}
	
	private static Map<String, List<Method>> getMethodMap(Class<?> testClass) 
	{
		Method[] declareMethod =  testClass.getDeclaredMethods();
		
		List<Method> AnnotationTest = new ArrayList<Method>();
		List<Method> AnnotationBeforeEach = new ArrayList<Method>();
		List<Method> AnnotationAfterEach = new ArrayList<Method>();
		List<Method> AnnotationBeforeAll = new ArrayList<Method>();
		List<Method> AnnotationAfterAll = new ArrayList<Method>();
		
		for(Method m: declareMethod)
    	{	
    		if(m.isAnnotationPresent(BeforeAll.class)) 
    		{
    			AnnotationBeforeAll.add(m);
    		}else if(m.isAnnotationPresent(AfterAll.class)) 
    		{
    			AnnotationAfterAll.add(m);
    		}else if (m.isAnnotationPresent(Test.class))
    		{
    			AnnotationTest.add(m);
    		}else if(m.isAnnotationPresent(BeforeEach.class)) 
    		{
    			AnnotationBeforeEach.add(m);
    		}else if(m.isAnnotationPresent(AfterEach.class)) 
    		{
    			AnnotationAfterEach.add(m);
    		}
    	}
		
		Map<String, List<Method>> methodMap = new HashMap<String, List<Method>>();
		methodMap.put("AnnotationBeforeAll", AnnotationBeforeAll);
		methodMap.put("AnnotationAfterAll", AnnotationAfterAll);
		methodMap.put("AnnotationBeforeEach", AnnotationBeforeEach);
		methodMap.put("AnnotationAfterEach", AnnotationAfterEach);
		methodMap.put("AnnotationTest", AnnotationTest);
		
		return methodMap;
	}
	
	private static void start(Method m, Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		m.setAccessible(true);
		m.invoke(o);
		m.setAccessible(false);
	}
	
	private static Object CreateObject(Constructor<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		c.setAccessible(true);
		Object o = c.newInstance();
		c.setAccessible(false);
		
		return o;
	}
	
	public static void run(Class<?> testClass) {
    	
    	int testAll = 0, testCompleted = 0;
    	String testInfo = "";
    	
    	try {
	    	Object obj = null;
	
	    	Map<String, List<Method>> methodMap =  getMethodMap(testClass);
	    	
	    	if (methodMap.containsKey("AnnotationTest")) 
	    	{
	    		
	    		testAll = methodMap.get("AnnotationTest").size();
	    		
		    	try 
		    	{		    		
		    		if (methodMap.containsKey("AnnotationBeforeAll")) 
			    	{
			    		for(Method m: methodMap.get("AnnotationBeforeAll")) 
			    		{
			    			start(m, null);
			    		}
			    	}
			    	
			    	for(Method t: methodMap.get("AnnotationTest")) 
			    	{
			    		obj = CreateObject(testClass.getDeclaredConstructor());
			    		
			    		try 
			    		{
					    	if (methodMap.containsKey("AnnotationBeforeEach")) 
					    	{
					        	for(Method b: methodMap.get("AnnotationBeforeEach"))
					        	{
					        		start(b, obj);
					        	}
					    	}
				        	
					    	try
					    	{
					    		start(t, obj);
					    		testCompleted++;
					    		testInfo += t.getName() + " completed" + System.lineSeparator();
					    	}
					    	catch(Exception e) 
					    	{
					    		testInfo += t.getName() + " failed" + System.lineSeparator();
					    		e.printStackTrace();
					    	}
						} 
						catch(Exception e) 
						{
							e.printStackTrace();
							break;
						}
			    		finally 
			    		{
					    	if (methodMap.containsKey("AnnotationAfterEach")) 
					    	{
					        	for(Method a: methodMap.get("AnnotationAfterEach"))
					        	{
					        		start(a, obj);
					        	}
					    	}
						}
			    	}
		    	}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
		    	finally 
		    	{
			    	if (methodMap.containsKey("AnnotationAfterAll")) 
			    	{
			    		for(Method m: methodMap.get("AnnotationAfterAll")) 
			    		{
			    			start(m, null);
			    		}
			    	}
				}
	    	}
	    	else 
	    	{
	    		throw new NoSuchElementException("There are no tests in class ." + testClass.getName());
	    	}
    	}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
    	finally 
    	{
    		System.out.println(System.lineSeparator() + "Test information:");
    		System.out.println("all test = " + testAll + "; test completed = " + testCompleted + "; test failed = " + (testAll - testCompleted));
    		System.out.println(testInfo);
    	}
    }
}
