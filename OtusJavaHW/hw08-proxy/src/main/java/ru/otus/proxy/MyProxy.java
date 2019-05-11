package ru.otus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import ru.otus.classes.TestLoggingImpl;
import ru.otus.handlers.InvocationHandlers;

final public class MyProxy {
    static public Object TestLogging() {
    	
    	Object myClass= new TestLoggingImpl();
    	
    	Class<?>[] interf =  myClass.getClass().getInterfaces();
    	
        InvocationHandler handler = new InvocationHandlers.TestLogging(myClass);
        
        return (Object) Proxy.newProxyInstance(myClass.getClass().getClassLoader(),interf, handler);
    }
}
