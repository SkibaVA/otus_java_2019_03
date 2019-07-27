package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.ATMStrategy;
import ru.otus.interfaces.ICell;

public class ATMStrategyMakeMoney implements ATMStrategy{

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
			//throw new Error(ex.getMessage());
			return false;
		}
		
		return true;
	}

}
