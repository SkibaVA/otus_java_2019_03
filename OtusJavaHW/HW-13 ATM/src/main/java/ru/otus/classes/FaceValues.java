package ru.otus.classes;

public enum FaceValues {
	FIVE_THOUSAND(5000), 
	TWO_THOUSAND(2000), 
	THOUSAND(1000), 
	FIVE_HUNDRED(500), 
	TWO_HUNDRED(200), 
	HUNDRED(100),
	FIFTY(50), 
	TEN(10);

	private final int Value;

	private FaceValues(int Value) {
		this.Value = Value;
	}

	public int getValue() {
		return Value;
	}
}
