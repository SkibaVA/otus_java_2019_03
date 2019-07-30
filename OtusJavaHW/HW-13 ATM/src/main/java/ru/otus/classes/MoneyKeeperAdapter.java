package ru.otus.classes;

import ru.otus.interfaces.IATMDepartment;
import ru.otus.interfaces.IMoneyKeeper;

public class MoneyKeeperAdapter implements IATMDepartment{

	IATMDepartment keeper;
	
	public MoneyKeeperAdapter(IMoneyKeeper keeper) {
		this.keeper = (IATMDepartment)keeper;
	}
	
	@Override
	public int balance() {
		return keeper.balance();
	}

	@Override
	public void restoreATMState() {
		keeper.restoreATMState();
	}

}
