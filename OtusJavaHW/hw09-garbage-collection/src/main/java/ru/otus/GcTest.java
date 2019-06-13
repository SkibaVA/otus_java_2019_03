package ru.otus;

public class GcTest {

	public static void main(String[] args) throws InterruptedException {
		
		GcStatistic statistic = new GcStatistic();
		GcMonitoring.startMonitoring();
		
		long iter = 100000000;
		
		long beginTime = System.currentTimeMillis();
		try {
			Application.Run(iter);
		}
        catch (OutOfMemoryError e) 
		{
            System.out.println("OutOfMemoryError");
            statistic.printStatistics();
        }
		catch(InterruptedException e) 
		{
			System.out.println("InterruptedException");
		}
		System.out.println("Application time: " + (System.currentTimeMillis() - beginTime)/1000);
	}
}

