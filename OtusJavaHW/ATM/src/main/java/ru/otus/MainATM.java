package ru.otus;

import ru.otus.atm.ATM;
import ru.otus.moneyOperations.IBasicMoneyOperations;

public class MainATM {

	public static void main(String[] args) {
		IBasicMoneyOperations m = new ATM();
		System.out.println("Остаток: " + m.sum());
		m.makeMoney(9860);
		System.out.println("Остаток: " + m.sum());
		System.out.println("Выдано: " + m.giveMoney(7150));
		System.out.println("Остаток: " + m.sum());
	}

}
