package ru.otus;

public final class GcStatistic 
{
	private static int minorCount;
	private static int majorCount;
	private static long minorTime;
	private static long majorTime;
	private static long iterationCount;
	
	public GcStatistic() 
	{
		iterationCount = minorTime = majorTime = minorCount = majorCount = 0;
	}
	
	public static long getIterationCount() 
	{
		return iterationCount;
	}
	
	public static void incrementIterationCount() 
	{
		iterationCount++;
	}
	
	public static int getMinorCount() 
	{
		return minorCount;
	}
	
	public static void incrementMinorCount() 
	{
		minorCount++;
	}
	
	public static int getMajorCount() 
	{
		return majorCount;
	}
	
	public static void incrementMajorCount() 
	{
		majorCount++;
	}
	
	public static long getMinorTime() 
	{
		return minorTime;
	}
	
	public static void incrementMinorTime(long time) 
	{
		minorTime += time;
	}
	
	public static long getMajorTime() 
	{
		return majorTime;
	}
	
	public static void incrementMajorTime(long time) 
	{
		majorTime += time;
	}
	
	public void printStatistics() 
	{
        System.out.println("Minor count: " + Integer.toString(GcStatistic.getMinorCount()) + ", time: " + Long.toString(GcStatistic.getMinorTime()));
        System.out.println("Major count: " + Integer.toString(GcStatistic.getMajorCount()) + ", time: " + Long.toString(GcStatistic.getMajorTime()));
        System.out.println("Количество итераций: " + Long.toString(getIterationCount()));
	}
}
