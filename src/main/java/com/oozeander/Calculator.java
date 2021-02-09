package com.oozeander;

import java.util.ArrayList;
import java.util.List;

import com.oozeander.service.CalculatorService;

public class Calculator {
	private List<String> list = new ArrayList<>();
	private CalculatorService calculatorService;

	public Calculator() {
	}

	public Calculator(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	public int add(int a, int b) {
		return calculatorService.add(a, b);
	}

	public int divide(int a, int b) {
		return calculatorService.divide(a, b);
	}

	public boolean isEven(int a) {
		return calculatorService.isEven(a);
	}

	public void addElementToList(String element, List<String> iterable) {
		calculatorService.addElementToList(element, iterable);
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}