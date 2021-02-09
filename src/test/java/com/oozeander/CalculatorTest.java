package com.oozeander;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.oozeander.service.CalculatorService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
	@Mock
	private CalculatorService calculatorService;
	@InjectMocks
	private Calculator calculator;
	@Captor
	ArgumentCaptor<Integer> intCaptor;
	// Used to inspect args (useful when we can't access the argument outside of the
	// method we'd like to test)
	@Spy
	List<String> list = new ArrayList<>(); // Partial Mocks

	/*
	 * @Before public void setup() { initMocks(this); } if no RunWith
	 */

	@Test
	public void test_mock_verify() {
		// GIVEN
		when(calculatorService.add(any(Integer.class), any(Integer.class))).thenReturn(3);
		// WHEN
		int expected = 3, actual = calculator.add(5, 18);
		// THEN
		assertThat(actual).isEqualTo(expected);
		verify(calculatorService, atLeastOnce()).add(isA(Integer.class), isA(Integer.class));
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void test_captor() {
		when(calculatorService.divide(isA(Integer.class), eq(0))).thenThrow(IllegalArgumentException.class);

		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> calculator.divide(18, 0));

		verify(calculatorService, only()).divide(intCaptor.capture(), intCaptor.capture());

		assertThat(intCaptor.getAllValues()).containsExactly(18, 0);
	}

	// Argument Matchers : any(Class), eq(value), isA()
	// verify => times(n), never(), atLeast(n), atLeastOnce(), only()
	// doNothing for void methods to capture args

	@Test
	public void test_spy() {
		// Real METHOD
		list.add("Billel");
		list.add("KETROUCI");

		assertThat(list).containsExactly("Billel", "KETROUCI");
		// Mocking
		doReturn(101).when(list).size();

		assertThat(list.size()).isEqualTo(101);
	}

	/*
	 * Answer FunctionalInterface
	 *
	 * When return simple => doReturn / thenReturn, Else => doAnswer / then /
	 * thenAnswer
	 */

	/*
	 * when()... => expects a type return of method, if methood returns void =>
	 * doReturn(), doThrow(), doAnswer(), doCallRealMethod(), doNothing()...
	 */

	@Test
	public void test_answer() {
		// Reverse isEven behavior
		doAnswer(invocation -> {
			final Integer a = invocation.getArgumentAt(0, Integer.class);
			return a % 2 == 0 ? false : true;
		}).when(calculatorService).isEven(any(Integer.class));

		assertThat(calculator.isEven(42)).isFalse();
	}
}