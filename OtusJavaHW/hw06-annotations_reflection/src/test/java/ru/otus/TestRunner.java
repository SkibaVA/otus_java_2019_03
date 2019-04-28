package ru.otus;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		run(AnnotationsTest.class);
	}

    private static void run(Class<?> testClass) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException  {
    	
    	Object obj = null;
    	List<Method> AnnotationTest = new ArrayList<Method>();
    	List<Method> AnnotationBeforeEach = new ArrayList<Method>();
    	List<Method> AnnotationAfterEach = new ArrayList<Method>();
    	Method[] declareMethod =  testClass.getDeclaredMethods();
    	
    	for(Method m: declareMethod)
    	{	
    		if(m.isAnnotationPresent(BeforeAll.class)) 
    		{
    			m.invoke(null);
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
    	
    	for(Method t: AnnotationTest) 
    	{
    		obj = testClass.getDeclaredConstructor().newInstance();
        	for(Method b: AnnotationBeforeEach)
        	{
        		b.invoke(obj);
        	}
        	
        	t.invoke(obj);
        	
        	for(Method a: AnnotationAfterEach)
        	{
        		a.invoke(obj);
        	}
    	}
    	
    	for(Method m: declareMethod)
    	{    		
    		if(m.isAnnotationPresent(AfterAll.class)) 
    		{
    			m.invoke(null);
    		}
    	}
    }
	
}
