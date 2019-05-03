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
	
	final private static class TestingContext
	{
		private enum Annotations
		{
			BEFORE_ALL("BeforeAll"),
			AFTER_ALL("AfterAll"),
			BEFORE_EACH("BeforeEach"),
			AFTER_EACH("AfterEach"),
			TEST("Test");
			
			private String name;
			
			Annotations(String name)
			{
				this.name = name;
			}
			
			public String getName() 
			{
				return name;	
			}
		}
		
		private Class<?> clazz = null;
		private Map<String, List<Method>> methodMap = new HashMap<String, List<Method>>();
		
		public TestingContext(Class<?> clazz)
		{
			this.clazz = clazz;
			setMethodMap();
		}
	
		private void setMethodMap()
		{
			Method[] declareMethod =  clazz.getDeclaredMethods();
			
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
			
			methodMap.put(Annotations.BEFORE_ALL.getName(), AnnotationBeforeAll);
			methodMap.put(Annotations.AFTER_ALL.getName(), AnnotationAfterAll);
			methodMap.put(Annotations.BEFORE_EACH.getName(), AnnotationBeforeEach);
			methodMap.put(Annotations.AFTER_EACH.getName(), AnnotationAfterEach);
			methodMap.put(Annotations.TEST.getName(), AnnotationTest);
		}
	
		public Map<String, List<Method>> getMethodMap()
		{
			return methodMap;
		}
		
		public void executeAsAccessible(Method m, Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
		{
			m.setAccessible(true);
			m.invoke(o);
			m.setAccessible(false);
		}
		
		public Object сreateObject(Constructor<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
		{
			c.setAccessible(true);
			Object o = c.newInstance();
			c.setAccessible(false);
			
			return o;
		}
		
		public void executeByKey(String key, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException 
		{
    		if (methodMap.containsKey(key)) 
	    	{
	    		for(Method m: methodMap.get(key)) 
	    		{
	    			executeAsAccessible(m, obj);
	    		}
	    	}
		}
		
	}
	
	public static void run(Class<?> testClass) {
    	
    	int testAll = 0, testCompleted = 0;
    	String testInfo = "";
    	
    	try {
	    	Object obj = null;    	
	    	
	    	TestingContext context = new TestingContext(testClass);    	
	    	Map<String, List<Method>> methodMap = context.getMethodMap();
	    	
//	    	Map<String, List<Method>> qwe = context.methodMap;
//	    	context.clazz = testClass;
	    	
	    	if (methodMap.containsKey(TestingContext.Annotations.TEST.getName())) 
	    	{
	    		
	    		testAll = methodMap.get(TestingContext.Annotations.TEST.getName()).size();
	    		
		    	try 
		    	{		    		
		    		context.executeByKey(TestingContext.Annotations.BEFORE_ALL.getName(), null);
		    		
			    	for(Method t: methodMap.get(TestingContext.Annotations.TEST.getName())) 
			    	{
			    		obj = context.сreateObject(testClass.getDeclaredConstructor());
			    		
			    		try 
			    		{
			    			context.executeByKey(TestingContext.Annotations.BEFORE_EACH.getName(), obj);
			    			
					    	try
					    	{
					    		context.executeAsAccessible(t, obj);
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
					    	context.executeByKey(TestingContext.Annotations.AFTER_EACH.getName(), obj);
						}
			    	}
		    	}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
		    	finally 
		    	{
			    	context.executeByKey(TestingContext.Annotations.AFTER_ALL.getName(), null);
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
