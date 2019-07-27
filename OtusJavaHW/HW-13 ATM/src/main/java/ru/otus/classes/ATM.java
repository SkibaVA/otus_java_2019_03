package ru.otus.classes;

import java.util.SortedSet;
import java.util.TreeSet;

import ru.otus.interfaces.ICell;
import ru.otus.interfaces.IMoneyKeeper;

public class ATM implements IMoneyKeeper{

	private SortedSet<ICell> cells;
	private CellFactory factory;  
	private ATMContext context;
	private ATMHistory history;
	private ATMStrategyMakeMoney makeMoney;
	private ATMStrategyGiveMoney giveMoney;
	
	public ATM() {
		factory = new DefaultCellFactory();
		cells = new TreeSet<ICell>();
		cells = factory.createDefaultCellSet(); 
		
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
			System.out.println("Balance " + cell.getFaceValues() + ": " +  cell.balance());
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
			restoreATMState();
			System.out.println("Указанная сумма не может быть выдана");
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
			restoreATMState();
			System.out.println("Не удалось положить деньги на счет.");
		}
		//------->>
		restoreATMState();
		
	}

	private void validateMoney(int money) throws IllegalArgumentException {
		if(money%10 != 0)
			throw new IllegalArgumentException("Неверная сумма");
	}
	
	
	public void restoreATMState() {
		ATMMemento memento = history.pop();
		cells =new TreeSet<ICell>(memento.getCells());
	}
	
	private void saveATMState() {
		history.push(new ATMMemento());
	}
	
	public class ATMMemento {
		private SortedSet<ICell> mementoCells;
		
		public ATMMemento () {
			this.mementoCells = new TreeSet<ICell>(cells);
			
			for (ICell cell : mementoCells) {
				System.out.println("------->>>>Balance " + cell.getFaceValues() + ": " +  cell.balance());
			}
			
		}
		
		public SortedSet<ICell> getCells(){
			
			for (ICell cell : mementoCells) {
				System.out.println("------->>>>---------Balance " + cell.getFaceValues() + ": " +  cell.balance());
			}
			
			return mementoCells;
		}
	}
	
}
