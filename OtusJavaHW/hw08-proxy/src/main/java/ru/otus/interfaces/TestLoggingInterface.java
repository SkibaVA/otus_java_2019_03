package ru.otus.interfaces;

import ru.otus.annotations.Log;

public interface TestLoggingInterface {
	
	public void calculation(int param);
	
	@Log
	public void calculation(double param);
}