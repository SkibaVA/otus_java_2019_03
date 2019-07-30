package ru.otus;

import ru.otus.classes.ATM;
import ru.otus.classes.Department;
import ru.otus.interfaces.IMoneyKeeper;

public class Application {

	public static void main(String[] args) {
		
		Department department = Department.getInstance();
		
		IMoneyKeeper atm1 = new ATM();
		atm1.makeMoney(150);
		
		IMoneyKeeper atm2 = new ATM();
		atm2.makeMoney(200);
		
		department.subscribe(atm1);
		department.subscribe(atm2);
		
		department.balance();
		
		atm2.makeMoney(200);
		atm1.giveMoney(50);
		
		department.balance();
		department.restoreATMState();
		department.balance();
		
		department.unsubscribe(atm2);
		department.balance();
		

	}

}
