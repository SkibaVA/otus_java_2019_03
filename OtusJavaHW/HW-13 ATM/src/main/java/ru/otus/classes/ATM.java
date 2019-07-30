package ru.otus.classes;

import java.util.SortedSet;

import ru.otus.interfaces.IATMDepartment;
import ru.otus.interfaces.ICell;
import ru.otus.interfaces.IMoneyKeeper;

public class ATM implements IMoneyKeeper, IATMDepartment{

	private SortedSet<ICell> cells;
	private CellFactory factory;  
	private ATMContext context;
	private ATMHistory history;
	private ATMStrategyMakeMoney makeMoney;
	private ATMStrategyGiveMoney giveMoney;
	
	public ATM() {		
		factory = new DefaultCellFactory();
		cells = factory.createCellSet(); 
		
		context = new ATMContext();
		makeMoney = new ATMStrategyMakeMoney();
		giveMoney = new ATMStrategyGiveMoney();
		
		history = new ATMHistory();
	}
	
	
	@Override
	public int balance() {
		int balance = 0;
		for (ICell cell : cells) {
			balance += cell.balance();
		}
		return balance;
	}

	@Override
	public int giveMoney(int money){
		validateMoney(money);
		
		if (this.balance() < money) {
			System.out.println("Сумма привешает депозит.");
			return 0;
		}
		
		saveATMState();
		
		context.setStrategy(giveMoney);
		if(context.executeStrategy(money, cells)) {
			System.out.println("Выдано " + money + "р.");
			return money;
		}
		else {
			System.out.println("Указанная сумма не может быть выдана");
			restoreATMState();
			return 0;
		}	
	}

	@Override
	public void makeMoney(int money) throws IllegalArgumentException {
		validateMoney(money);
		
		saveATMState();
		
		context.setStrategy(makeMoney);
		if(context.executeStrategy(money, cells)) {
			System.out.println("Сумма в размере " + money + "р. принята.");
		}
		else {
			System.out.println("Не удалось положить деньги на счет.");
			restoreATMState();
		}
		
	}

	private void validateMoney(int money) throws IllegalArgumentException {
		if(money%10 != 0)
			throw new IllegalArgumentException("Неверная сумма");
	}
	
	
	public void restoreATMState() {
		ATMMemento memento = history.pop();
		cells = memento.getCells();
	}
	
	private void saveATMState() {
		history.push(new ATMMemento());
	}
	
	public class ATMMemento {
		private SortedSet<ICell> mementoCells;
		
		public ATMMemento () {
			mementoCells = CopySet(cells);			
		}
		
		public SortedSet<ICell> getCells(){	
			return CopySet(mementoCells);
		}
		
		private SortedSet<ICell>  CopySet(SortedSet<ICell> cells) {
			factory = new EmptyCellFactory();
			SortedSet<ICell> copy = factory.createCellSet();
			for (ICell cell : cells) {
				copy.add(cell.copy());
			}
			
			return copy;
		}
	}
	
}
