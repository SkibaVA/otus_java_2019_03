package ru.otus.interfaces;

public interface IMoneyKeeper extends IBalance{

	int giveMoney(int bills);

	void makeMoney(int bills);
}
