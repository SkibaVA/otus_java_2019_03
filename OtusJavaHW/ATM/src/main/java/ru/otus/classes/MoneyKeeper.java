package ru.otus.classes;

import ru.otus.interfaces.IMoneyKeeper;

public class MoneyKeeper implements IMoneyKeeper {

	private FaceValues faceValue;
	private int quantityBills;

	public MoneyKeeper(FaceValues faceValue, int quantityBills) {
		this.faceValue = faceValue;
		this.quantityBills = quantityBills;
	}

	@Override
	public int getFaceValues() {
		return faceValue.getValue();
	}

	@Override
	public int sum() {
		return faceValue.getValue() * quantityBills;
	}

	@Override
	public int giveMoney(int bill) {
		int count = 0;
		if (quantityBills < bill) {
			count = quantityBills;
			quantityBills = 0;
		} else {
			count = bill;
			quantityBills = quantityBills - bill;
		}
		return count;
	}

	@Override
	public void makeMoney(int bill) {
		quantityBills = quantityBills + bill;
	}

}
