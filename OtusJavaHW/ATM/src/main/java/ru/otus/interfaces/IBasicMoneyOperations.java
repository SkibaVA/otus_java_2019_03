/**
 * 
 */
package ru.otus.interfaces;

/**
 * @author Skiba Vadim
 * 
 *         Интерфейс основных операций с деньгами.
 */
public interface IBasicMoneyOperations {
	int sum();

	int giveMoney(int money);

	void makeMoney(int money);
}
