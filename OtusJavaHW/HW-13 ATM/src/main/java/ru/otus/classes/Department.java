package ru.otus.classes;

import java.util.HashMap;
import java.util.Map;

import ru.otus.interfaces.IATMDepartment;
import ru.otus.interfaces.IMoneyKeeper;

public final class Department implements IATMDepartment{
	
	private static Department instance;
	private Map<IMoneyKeeper, IATMDepartment> department;
	
	private  Department() {
		department = new HashMap<IMoneyKeeper, IATMDepartment>();
	}
	
    public static Department getInstance() {
        if (instance == null) {
            instance = new Department();
        }
        return instance;
    }
    
    public void subscribe(IMoneyKeeper atm) {
    	department.put(atm, new MoneyKeeperAdapter(atm));
    }
    
    public void unsubscribe(IMoneyKeeper atm) {
    	department.remove(atm);
    }

	@Override
	public int balance() {
		int sum =  department.entrySet().stream().mapToInt(v -> v.getValue().balance()).sum();
		System.out.println("Общий баланс всех банкоматов: " + sum);
		return sum;
	}

	@Override
	public void restoreATMState() {
		department.entrySet().forEach(v -> v.getValue().restoreATMState());
		System.out.println("Состояние всех банкоматов восстановлено.");
	}
}
