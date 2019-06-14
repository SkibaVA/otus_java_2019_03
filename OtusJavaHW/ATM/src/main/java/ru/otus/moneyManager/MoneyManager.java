package ru.otus.moneyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.otus.FaceValues;
import ru.otus.moneyKeeper.MoneyKeeper;
import ru.otus.moneyOperations.IBasicMoneyOperations;

public class MoneyManager implements IBasicMoneyOperations
{

	private Map<FaceValues, IBasicMoneyOperations> moneyKeepers = new HashMap<FaceValues, IBasicMoneyOperations>();
	
	public MoneyManager() 
	{
		for(FaceValues fv : FaceValues.values())
		{
			moneyKeepers.put(fv, new MoneyKeeper(fv, 0));
		}
	}
	
	@Override
	public int sum() 
	{		
		return new ArrayList<IBasicMoneyOperations>(moneyKeepers.values())
				.stream()		
				.mapToInt(m -> m.sum())
				.sum();
	}

	@Override
	public int giveMoney(int money) throws IllegalArgumentException
	{
		if(this.sum() < money) 
		{
			throw new IllegalArgumentException("Сумма привешает депозит.");
		}
		
		int bill = 0;
		int outMoney = money;
		
		for(FaceValues fv : FaceValues.values())
		{
			bill = (int)(money/fv.getValue());
			money -= moneyKeepers.get(fv).giveMoney(bill) * fv.getValue();
			if(money == 0) break;
		}
		
		if(money != 0) 
		{
			throw new IllegalArgumentException("Данная сумма не может быть выдана.");
		}
		
		return outMoney;
	}

	@Override
	public void makeMoney(int money) throws IllegalArgumentException
	{
		if(money%10 != 0)
		{
			throw new IllegalArgumentException("Введена неверная сумма.");
		}
		
		int bill = 0;		
		
		for(FaceValues fv : FaceValues.values())
		{
			bill = (int)(money/fv.getValue());
			moneyKeepers.get(fv).makeMoney(bill);
			money -= fv.getValue() * bill;
			if(money == 0) break;
		}
	}
}
