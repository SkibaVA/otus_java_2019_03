package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.ATMStrategy;
import ru.otus.interfaces.ICell;

public class ATMContext {
	private ATMStrategy strategy;
	
    public void setStrategy(ATMStrategy strategy) {
    	this.strategy = strategy;
    }

    public boolean executeStrategy(int money, SortedSet<ICell> cells) {
	    return strategy.execute(money, cells);
	}
}