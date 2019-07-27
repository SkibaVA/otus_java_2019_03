package ru.otus.interfaces;

import java.util.SortedSet;

public interface ATMStrategy {
	boolean execute( int money, SortedSet<ICell> cells);
}
