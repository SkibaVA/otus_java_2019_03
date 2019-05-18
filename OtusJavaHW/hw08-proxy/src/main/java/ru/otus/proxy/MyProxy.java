package ru.otus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import ru.otus.annotations.Log;
import ru.otus.classes.TestLoggingImpl;
import ru.otus.handlers.InvocationHandlers;
import ru.otus.interfaces.ICommander;

final public class MyProxy {
    static public ICommander TestLogging() {
    	
    	ICommander myClass = new TestLoggingImpl();
    	Class<?> clazz = myClass.getClass();
    	Class<?>[] interf =  clazz.getInterfaces();
    	
    	Map<String, Method> methodMap = new HashMap<String, Method>();
    	
    	for(Method m: clazz.getMethods()) {
    		if(m.isAnnotationPresent(Log.class)) {
    			methodMap.put(m.getName(), m);
    		}
    	}
    	
        InvocationHandler handler = new InvocationHandlers.TestLogging(myClass, methodMap);
        
        return (ICommander) Proxy.newProxyInstance(myClass.getClass().getClassLoader(),interf, handler);
    }
}
