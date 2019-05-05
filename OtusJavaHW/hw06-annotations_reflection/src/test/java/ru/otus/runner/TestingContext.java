package ru.otus.runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.otus.Annotations.*;

class TestingContext {
	private Class<?> clazz = null;
	private Map<Annotations, List<Method>> methodMap = new HashMap<Annotations, List<Method>>();
	
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
		
		methodMap.put(Annotations.BEFORE_ALL, AnnotationBeforeAll);
		methodMap.put(Annotations.AFTER_ALL, AnnotationAfterAll);
		methodMap.put(Annotations.BEFORE_EACH, AnnotationBeforeEach);
		methodMap.put(Annotations.AFTER_EACH, AnnotationAfterEach);
		methodMap.put(Annotations.TEST, AnnotationTest);
	}

	public Map<Annotations, List<Method>> getMethodMap()
	{
		return methodMap;
	}		
}
