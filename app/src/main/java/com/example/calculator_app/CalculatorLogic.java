package com.example.calculator_app;

public class CalculatorLogic {

    public double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : Double.NaN; // handle divide by zero
            default: return b;
        }
    }

    public double sqrt(double value) {
        if (value < 0) throw new IllegalArgumentException("Cannot sqrt negative number");
        return Math.sqrt(value);
    }

    public double changeSign(double value) {
        return -value;
    }

    public double percent(double value) {
        return value / 100.0;
    }

    public String formatNumber(double value) {
        if (value % 1 == 0) {
            return String.valueOf((long) value);
        } else {
            // Remove trailing zeros after decimal
            return String.format("%.10f", value).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}
