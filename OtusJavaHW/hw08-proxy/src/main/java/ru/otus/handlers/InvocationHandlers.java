package ru.otus.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ru.otus.annotations.Log;
import ru.otus.interfaces.ICommander;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final public class InvocationHandlers {
	public static class TestLogging implements InvocationHandler 
	{
		
        private final ICommander myClass;
        private Set<String> methodSignatureSet;

        public TestLogging(ICommander myClass) {
            this.myClass = myClass;
            this.methodSignatureSet = new HashSet<String>();
            
            Class<?> clazz = this.myClass.getClass();
            
        	for(Method m: clazz.getMethods()) {
        		if(m.isAnnotationPresent(Log.class)) {
        			methodSignatureSet.add(getMethodSignature(m));
        		}
        	}
        }

        private String getMethodSignature(Method m) {
        	return m.getName() + "_" + Arrays.toString(m.getParameterTypes());
        }
        
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {	
			
			if(methodSignatureSet.contains(getMethodSignature(method))) {
				System.out.println("Object '" + myClass.getClass().getName() + "', executed method: " + method.getName() + ", param: " + Arrays.toString(args));
			}
			
			return method.invoke(myClass, args);
		}
	}
}
