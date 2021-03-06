package ru.otus.classes;

import ru.otus.interfaces.IBasicMoneyOperations;

public class ATM implements IBasicMoneyOperations {

	private IBasicMoneyOperations manager;

	public ATM() {
		manager = new MoneyManager();
	}

	@Override
	public int sum() {
		return manager.sum();
	}

	@Override
	public int giveMoney(int money) {
		return manager.giveMoney(money);
	}

	@Override
	public void makeMoney(int money) throws IllegalArgumentException {
		manager.makeMoney(money);
	}
}
