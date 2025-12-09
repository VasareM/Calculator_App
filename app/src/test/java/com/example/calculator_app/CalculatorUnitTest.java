package com.example.calculator_app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorUnitTest {

    private CalculatorLogic logic;

    @Before
    public void setUp() {
        logic = new CalculatorLogic();
    }

    @Test
    public void testAddition() {
        // Given
        double a = 2;
        double b = 3;
        String op = "+";

        // When
        double actual = logic.calculate(a, b, op);

        // Then
        double expected = 5;
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testSubtraction() {
        // Given
        double a = 2;
        double b = 3;
        String op = "-";

        // When
        double actual = logic.calculate(a, b, op);

        // Then
        double expected = -1;
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMultiplication() {
        // Given
        double a = 2;
        double b = 3;
        String op = "*";

        // When
        double actual = logic.calculate(a, b, op);

        // Then
        double expected = 6;
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testDivision() {
        // Given
        double a = 6;
        double b = 3;
        String op = "/";

        // When
        double actual = logic.calculate(a, b, op);

        // Then
        double expected = 2;
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testDivisionByZero() {
        // Given
        double a = 5;
        double b = 0;
        String op = "/";

        // When
        double actual = logic.calculate(a, b, op);

        // Then
        assertEquals(Double.NaN, actual, 0.0);
    }

    @Test
    public void testSqrtPositive() {
        // Given
        double value = 9;

        // When
        double actual = logic.sqrt(value);

        // Then
        double expected = 3;
        assertEquals(expected, actual, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtNegative() {
        // Given
        double value = -4;

        // When
        logic.sqrt(value);

        // Then
        // expect IllegalArgumentException
    }

    @Test
    public void testChangeSign() {
        // Given
        double value = 5;

        // When
        double actual = logic.changeSign(value);

        // Then
        double expected = -5;
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testPercent() {
        // Given
        double value = 50;

        // When
        double actual = logic.percent(value);

        // Then
        double expected = 0.5;
        assertEquals(expected, actual, 0.00001);
    }
}
