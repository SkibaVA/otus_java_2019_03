package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.IATMStrategy;
import ru.otus.interfaces.ICell;

public class ATMStrategyMakeMoney implements IATMStrategy{

	@Override
	public boolean execute(int money, SortedSet<ICell> cells) {
		
		int bills = 0;
		try {
			for (ICell cell : cells) {
				bills = (int)(money/cell.getFaceValues());
				cell.makeMoney(bills);
				money -= bills * cell.getFaceValues();
				if (money == 0)
					break;
			}
		}
		catch(Throwable ex) {
			return false;
		}
		
		return true;
	}

}
