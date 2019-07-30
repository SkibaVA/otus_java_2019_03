package ru.otus.classes;

import java.util.Stack;

import ru.otus.classes.ATM.ATMMemento;

public class ATMHistory {
	
	private Stack<ATMMemento> stack;
	
	public ATMHistory() {
		stack = new Stack<>();
	}
	
	public void push(ATMMemento memento) {
		stack.push(memento);
		
	}
	
	public ATMMemento pop(){
		return stack.pop();
	}
}
