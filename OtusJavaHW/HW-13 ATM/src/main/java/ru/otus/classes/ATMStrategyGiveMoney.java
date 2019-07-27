package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.ATMStrategy;
import ru.otus.interfaces.ICell;

public class ATMStrategyGiveMoney implements ATMStrategy{

	@Override
	public boolean execute(int money, SortedSet<ICell> cells) {
		
		int bills = 0;

		for (ICell cell : cells) {
			bills = (int)(money/cell.getFaceValues());
			money -= cell.giveMoney(bills) * cell.getFaceValues();
			if (money == 0)
				break;
		}

		if (money != 0)
			return false;
		
		return true;
	}

}
