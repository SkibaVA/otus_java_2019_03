package ru.otus.classes;

import java.util.ArrayList;
import java.util.List;

import ru.otus.interfaces.IBasicMoneyOperations;
import ru.otus.interfaces.IMoneyKeeper;
import ru.otus.interfaces.IMoneyManagerCommands;

public class MoneyManager implements IBasicMoneyOperations, IMoneyManagerCommands {

	private List<IMoneyKeeper> moneyKeepers = new ArrayList<IMoneyKeeper>();

	public MoneyManager() {
		for (FaceValues fv : FaceValues.values()) {
			moneyKeepers.add(new MoneyKeeper(fv, 0));
		}
	}

	@Override
	public int sum() {
		return moneyKeepers.stream().mapToInt(m -> m.sum()).sum();
	}

	@Override
	public int giveMoney(int money) throws IllegalArgumentException {
		if (this.sum() < money)
			throw new IllegalArgumentException("Сумма привешает депозит.");

		int bill = 0;
		int outMoney = money;

		for (IMoneyKeeper mk : moneyKeepers) {
			bill = decomposeSum(money, mk);
			money -= mk.giveMoney(bill) * mk.getFaceValues();
			if (money == 0)
				break;
		}

		if (money != 0)
			throw new IllegalArgumentException("Данная сумма не может быть выдана.");

		return outMoney;
	}

	@Override
	public void makeMoney(int money) throws IllegalArgumentException {
		if (money % 10 != 0)
			throw new IllegalArgumentException("Введена неверная сумма.");

		int bill = 0;

		for (IMoneyKeeper mk : moneyKeepers) {
			bill = decomposeSum(money, mk);
			mk.makeMoney(bill);
			money -= bill * mk.getFaceValues();
			if (money == 0)
				break;
		}
	}

	@Override
	public int decomposeSum(int money, IMoneyKeeper mk) {
		return (int) (money / mk.getFaceValues());
	}

}
