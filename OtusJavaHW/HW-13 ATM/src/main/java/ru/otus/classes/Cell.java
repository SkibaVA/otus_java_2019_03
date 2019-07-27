package ru.otus.classes;

import ru.otus.interfaces.ICell;

public final class Cell implements ICell{

	private FaceValues faceValue;
	private int quantityBills;
	
	public Cell(FaceValues faceValue,  int quantityBills) {
		this.faceValue = faceValue;
		this.quantityBills = quantityBills;
	}
	
	@Override
	public int getFaceValues() {
		return faceValue.getValue();
	}

	@Override
	public int getCount() {
		return quantityBills;
	}

	@Override
	public int balance() {
		return faceValue.getValue()*quantityBills;
	}

	@Override
	public int giveMoney(int bills) {
		int count = 0;
		if (quantityBills < bills) {
			count = quantityBills;
			quantityBills = 0;
		} else {
			count = bills;
			quantityBills -= bills;
		}
		return count;
	}

	@Override
	public void makeMoney(int bills) {
		quantityBills += bills;
	}

	@Override
	public ICell copy() {
		ICell cell = new Cell(faceValue, quantityBills);
		return cell;
	}

}
