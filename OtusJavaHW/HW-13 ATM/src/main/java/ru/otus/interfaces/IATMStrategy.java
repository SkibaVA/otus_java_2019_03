package ru.otus.interfaces;

import java.util.SortedSet;

public interface IATMStrategy {
	boolean execute( int money, SortedSet<ICell> cells);
}
