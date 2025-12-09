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
        double a = 2;
        double b = 3;
        String op = "+";
        double actual = logic.calculate(a, b, op);
        double expected = 5;
        assertEquals(expected, actual, 0.00001);
    }
    @Test
    public void testSubtraction() {
        double a = 2;
        double b = 3;
        String op = "-";
        double actual = logic.calculate(a, b, op);
        double expected = -1;
        assertEquals(expected, actual, 0.00001);
    }
    @Test
    public void testMultiplication() {
        double a = 2;
        double b = 3;
        String op = "*";
        double actual = logic.calculate(a, b, op);
        double expected = 6;
        assertEquals(expected, actual, 0.00001);
    }
    @Test
    public void testDivision() {
        double a = 6;
        double b = 3;
        String op = "/";
        double actual = logic.calculate(a, b, op);
        double expected = 2;
        assertEquals(expected, actual, 0.00001);
    }
    @Test
    public void testDivisionByZero() {
        double a = 5;
        double b = 0;
        String op = "/";
        double actual = logic.calculate(a, b, op);
        assertEquals(Double.NaN, actual, 0.0);
    }
    @Test
    public void testSqrtPositive() {
        double value = 9;
        double actual = logic.sqrt(value);
        double expected = 3;
        assertEquals(expected, actual, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSqrtNegative() {
        double value = -4;
        logic.sqrt(value);
    }
    @Test
    public void testChangeSign() {
        double value = 5;
        double actual = logic.changeSign(value);
        double expected = -5;
        assertEquals(expected, actual, 0.00001);
    }
    @Test
    public void testPercent() {
        double value = 50;
        double actual = logic.percent(value);
        double expected = 0.5;
        assertEquals(expected, actual, 0.00001);
    }
}
