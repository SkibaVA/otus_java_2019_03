package ru.otus.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import ru.otus.annotations.Log;

final public class InvocationHandlers {
	public static class TestLogging implements InvocationHandler 
	{
		
        private final Object myClass;

        public TestLogging(Object myClass) {
            this.myClass = myClass;
        }

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {	
			
			if(method.isAnnotationPresent(Log.class)) {
				System.out.println("Object '" + myClass.getClass().getName() + "', executed method: " + method.getName() + ", param: " + Arrays.toString(args));
			}
			
			return method.invoke(myClass, args);
		}
	}
}
