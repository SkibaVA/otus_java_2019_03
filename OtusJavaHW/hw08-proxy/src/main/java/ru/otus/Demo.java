package ru.otus;

import ru.otus.interfaces.ICommander;
import ru.otus.proxy.MyProxy;

public class Demo {

	public static void main(String[] args) {
		
		ICommander myObj = MyProxy.TestLogging();
		
		myObj.calculation(11);
		
		myObj.calculation(15.0);
		
		myObj.print("Hello");
		
	}

}
