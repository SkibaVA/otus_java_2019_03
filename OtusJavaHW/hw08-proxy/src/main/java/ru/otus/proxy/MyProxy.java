package ru.otus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import ru.otus.classes.TestLoggingImpl;
import ru.otus.handlers.InvocationHandlers;
import ru.otus.interfaces.ICommander;

final public class MyProxy {
    static public ICommander TestLogging() {
    	
    	ICommander myClass = new TestLoggingImpl();
    	
        InvocationHandler handler = new InvocationHandlers.TestLogging(myClass);
        
        return (ICommander) Proxy.newProxyInstance(
	        		myClass.getClass().getClassLoader()
	        		,myClass.getClass().getInterfaces()
	        		,handler
        		);
    }
}
