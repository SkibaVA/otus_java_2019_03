package ru.otus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
	public static void Run(long iter) throws InterruptedException 
	{
		long loopCounter = iter;
		List<String> list = new ArrayList<String>();
		
		Thread.sleep(120000);
		for(int i = 0; i <= loopCounter; i++) 
		{
			Collections.addAll(list, new String[]{ Integer.toString(i), Integer.toString(i*2), Integer.toString(i*3) , Integer.toString(i*4)});
			list.remove(list.size() - 1);
			list.remove(list.size() - 1);
			GcStatistic.incrementIterationCount();
		}
		Thread.sleep(120000);
	}
}
