package ru.otus.classes;

public enum FaceValues {
	TEN(10), 
	FIFTY(50), 
	HUNDRED(100),
	TWO_HUNDRED(200),
	FIVE_HUNDRED(500), 
	THOUSAND(1000), 
	TWO_THOUSAND(2000), 
	FIVE_THOUSAND(5000);

	private final int Value;

	private FaceValues(int Value) {
		this.Value = Value;
	}

	public int getValue() {
		return Value;
	}
}
