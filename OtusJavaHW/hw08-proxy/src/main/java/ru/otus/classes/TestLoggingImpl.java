package ru.otus.classes;

import ru.otus.annotations.Log;
import ru.otus.interfaces.ICommander;

public class TestLoggingImpl implements ICommander{

	public TestLoggingImpl() {}
	
	@Log
	@Override
	public void calculation(int param) {		
		System.out.println(param);
	}

	@Override
	public void calculation(double param) {
		System.out.println(param);
	}

	@Log
	@Override
	public void print(String param) {
		System.out.println(param);
	}

}
