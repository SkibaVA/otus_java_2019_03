package ru.otus.interfaces;

public interface IMoneyKeeper {
	int balance();

	int giveMoney(int bills);

	void makeMoney(int bills);
}
