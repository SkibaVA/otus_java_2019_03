package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.IATMStrategy;
import ru.otus.interfaces.ICell;

public class ATMContext {
	private IATMStrategy strategy;
	
    public void setStrategy(IATMStrategy strategy) {
    	this.strategy = strategy;
    }

    public boolean executeStrategy(int money, SortedSet<ICell> cells) {
	    return strategy.execute(money, cells);
	}
}