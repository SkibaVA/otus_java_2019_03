package ru.otus;

import java.util.*;

public class GenericsCustom {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		for(int i = 1; i <= 25; i++) 
		{
			list.add(i);
		}
		
		List<Integer> a = new DIYarrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		
		a.addAll(list);
		
		List<Integer> b = new DIYarrayList<>(a.size());
		Collections.copy(b,a);
		Collections.sort(b,null);
		
		for(int i = 1; i <= b.size(); i++) 
		{
			System.out.println(b.get(i-1).toString());
		}
	}
}
