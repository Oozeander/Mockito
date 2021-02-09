package com.oozeander.service;

import java.util.List;

public class CalculatorService {
	public int add(int a, int b) {
		return a + b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public boolean isEven(int a) {
		return a % 2 == 0;
	}

	public void addElementToList(String element, List<String> iterable) {
		iterable.add(element);
	}
}