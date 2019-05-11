package ru.otus;

import ru.otus.interfaces.OthesInterface;
import ru.otus.interfaces.TestLoggingInterface;
import ru.otus.proxy.MyProxy;

public class Demo {

	public static void main(String[] args) {
		
		Object myObj = MyProxy.TestLogging();
		
		((TestLoggingInterface)myObj).calculation(11);
		
		((TestLoggingInterface)myObj).calculation(15.0);
		
		((OthesInterface)myObj).print("Hello");
		
	}

}
