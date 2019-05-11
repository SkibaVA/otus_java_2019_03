package ru.otus.runner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import ru.otus.Annotations.*;

final public class Runner {
	
	private Runner() {
		throw new UnsupportedOperationException();
	}
	
	public static void run(Class<?> testClass) {
    	
    	int testAll = 0, testCompleted = 0;
    	String testInfo = "";
    	
    	try {
	    	Object obj = null;    	
	    	
	    	TestingContext context = new TestingContext(testClass);    	
	    	Map<Annotations, List<Method>> methodMap = context.getMethodMap();
	    	
	    	if (methodMap.containsKey(Annotations.TEST)) 
	    	{
	    		testAll = methodMap.get(Annotations.TEST).size();
	    		
		    	try 
		    	{		    		
		    		Helper.executeByKey(context, Annotations.BEFORE_ALL, null);
		    		
			    	for(Method t: methodMap.get(Annotations.TEST)) 
			    	{
			    		obj = Helper.сreateObject(testClass.getDeclaredConstructor());
			    		
			    		try 
			    		{
			    			Helper.executeByKey(context, Annotations.BEFORE_EACH, obj);
			    			
					    	try
					    	{
					    		Helper.executeAsAccessible(t, obj);
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
			    			Helper.executeByKey(context, Annotations.AFTER_EACH, obj);
						}
			    	}
		    	}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
		    	finally 
		    	{
		    		Helper.executeByKey(context, Annotations.AFTER_ALL, null);
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
