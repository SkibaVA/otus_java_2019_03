package ru.otus;

import java.lang.reflect.*; //?
import ru.otus.runner.Runner;
import ru.otus.tests.AnnotationsTest;

public class TestRunner {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Runner.run(AnnotationsTest.class);
	}
}
