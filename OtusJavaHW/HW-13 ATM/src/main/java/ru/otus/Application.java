package ru.otus;

import ru.otus.classes.ATM;
import ru.otus.interfaces.IMoneyKeeper;

public class Application {

	public static void main(String[] args) {
		
		IMoneyKeeper atm = new ATM();
		atm.balance();
		atm.makeMoney(350);
		atm.balance();
		atm.giveMoney(340);
		atm.balance();
		
//		ATM atm = new ATM();
//		atm.balance();
//		atm.makeMoney(350);
//		//atm.restoreATMState();
//		atm.balance();

	}

}
