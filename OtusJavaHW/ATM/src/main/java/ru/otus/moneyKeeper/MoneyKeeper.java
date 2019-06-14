package ru.otus.moneyKeeper;

import ru.otus.FaceValues;
import ru.otus.moneyOperations.IBasicMoneyOperations;

public class MoneyKeeper implements IBasicMoneyOperations
{
	
	private FaceValues faceValue;
	private int quantityBills;
	
	public MoneyKeeper(FaceValues faceValue, int quantityBills) 
	{
		this.faceValue = faceValue;
		this.quantityBills = quantityBills;
	}

	@Override
	public int sum() {
		return faceValue.getValue() * quantityBills;
	}

	@Override
	public int giveMoney(int money)
	{
		int bill = 0;
		if(quantityBills < money) 
		{
			bill = quantityBills;
			quantityBills = 0;
		}
		else 
		{
			bill = money;
			quantityBills = quantityBills - money;
		}
		return bill;
	}

	@Override
	public void makeMoney(int money) 
	{
		quantityBills = quantityBills + money;
	}

}
