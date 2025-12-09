package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay, tvExpression;
    String currentInput = "";
    double firstOperand = 0;
    String operator = "";

    CalculatorLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logic = new CalculatorLogic();
        tvDisplay = findViewById(R.id.tvDisplay);
        tvExpression = findViewById(R.id.tvExpression);

        setupNumberButtons();
        setupDotButton();
        setupOperators();
        setupSpecialButtons();
    }

    private void setupNumberButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        View.OnClickListener numberClickListener = v -> {
            Button b = (Button) v;
            currentInput += b.getText().toString();
            tvDisplay.setText(currentInput);
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }
    }

    private void setupDotButton() {
        Button btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                currentInput = currentInput.isEmpty() ? "0." : currentInput + ".";
                tvDisplay.setText(currentInput);
            }
        });
    }

    private void setupOperators() {
        setOperator(R.id.btnPlus, "+");
        setOperator(R.id.btnMinus, "-");
        setOperator(R.id.btnMulti, "*");
        setOperator(R.id.btnDivision, "/");

        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(v -> {
            if (operator.isEmpty() || currentInput.isEmpty()) {
                tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
                return;
            }
            double secondOperand = Double.parseDouble(currentInput);
            double result = logic.calculate(firstOperand, secondOperand, operator);

            String expressionText = tvExpression.getText().toString() + currentInput + " =";
            tvExpression.setText(expressionText);

            currentInput = String.valueOf(result);
            tvDisplay.setText(logic.formatNumber(result));
            operator = "";
        });
    }

    private void setupSpecialButtons() {
        Button btnSquareRoot = findViewById(R.id.btnSquareRoot);
        btnSquareRoot.setOnClickListener(v -> {
            if (currentInput.isEmpty()) {
                tvDisplay.setText(R.string.enter_calc);
                return;
            }
            double value = Double.parseDouble(currentInput);
            try {
                double result = logic.sqrt(value);
                currentInput = String.valueOf(result);
                tvDisplay.setText(logic.formatNumber(result));
                tvExpression.setText("√(" + logic.formatNumber(value) + ")");
            } catch (IllegalArgumentException e) {
                tvDisplay.setText(R.string.error_msg);
            }
        });

        Button btnChangeSign = findViewById(R.id.btnChangeSign);
        btnChangeSign.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = logic.changeSign(value);
                currentInput = logic.formatNumber(value);
                tvDisplay.setText(currentInput);
            }
        });

        Button btnPercent = findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                String original = logic.formatNumber(value);
                value = logic.percent(value);
                currentInput = logic.formatNumber(value);
                tvDisplay.setText(currentInput);
                tvExpression.setText(tvExpression.getText().toString() + original + "%");
            } else {
                tvDisplay.setText(R.string.enter_calc);
            }
        });

        // Delete last character
        Button btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        });

        // Clear Entry
        Button btnCe = findViewById(R.id.btnCe);
        btnCe.setOnClickListener(v -> {
            currentInput = "";
            tvDisplay.setText("");
        });

        // Clear All
        Button btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(v -> {
            currentInput = "";
            firstOperand = 0;
            operator = "";
            tvDisplay.setText("");
            tvExpression.setText("");
        });
    }

    private void setOperator(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (currentInput.isEmpty() && op.equals("-")) {
                currentInput = "-";
                tvDisplay.setText(currentInput);
                return;
            }

            if (!operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                firstOperand = logic.calculate(firstOperand, secondOperand, operator);
                tvDisplay.setText(logic.formatNumber(firstOperand));
            } else {
                firstOperand = Double.parseDouble(currentInput);
            }

            operator = op;
            tvExpression.setText(logic.formatNumber(firstOperand) + " " + operator + " ");
            currentInput = "";
        });
    }
}
