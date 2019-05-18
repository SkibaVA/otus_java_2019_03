package ru.otus.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import ru.otus.interfaces.ICommander;
import java.util.Arrays;
import java.util.Map;

final public class InvocationHandlers {
	public static class TestLogging implements InvocationHandler 
	{
		
        private final ICommander myClass;
        private Map<String, Method>  methodMap;

        public TestLogging(ICommander myClass, Map<String, Method> methodMap) {
            this.myClass = myClass;
            this.methodMap = methodMap;
        }

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {	
			
			if(methodMap.containsKey(method.getName())) {
				if(method.getParameterCount() == methodMap.get(method.getName()).getParameterCount()) {
					
					Class<?>[] paramLocalMethod = method.getParameterTypes();
					Class<?>[] paramMapMethod = methodMap.get(method.getName()).getParameterTypes();
					
					boolean print = true;
					
					for(int i = 0; i < method.getParameterCount(); i++) {
						if(paramLocalMethod[i].getName() != paramMapMethod[i].getName()) {
							print = false;
							break;
						}
					}
					
					if(print) {
						System.out.println("Object '" + myClass.getClass().getName() + "', executed method: " + method.getName() + ", param: " + Arrays.toString(args));
					}
					
				}
				
			}
			
			return method.invoke(myClass, args);
		}
	}
}
