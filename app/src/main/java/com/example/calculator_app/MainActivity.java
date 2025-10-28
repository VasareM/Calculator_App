package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay, tvExpression;
    String currentInput="";
    double firstOperand=0;
    String operator="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay=findViewById(R.id.tvDisplay);
        tvExpression=findViewById(R.id.tvExpression);
        // num buttons
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };
        View.OnClickListener numberClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                currentInput += b.getText().toString();
                tvDisplay.setText(currentInput);
            }
        };
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        Button btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.contains(".")) {
                    currentInput = currentInput.isEmpty() ? "0." : currentInput + ".";
                    tvDisplay.setText(currentInput);
                }
            }
        });
        setOperator(R.id.btnPlus, "+");
        setOperator(R.id.btnMinus, "-");
        setOperator(R.id.btnMulti, "*");
        setOperator(R.id.btnDivision, "/");

        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty() || currentInput.isEmpty()) {
                    tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
                    return;
                }
                double secondOperand = Double.parseDouble(currentInput);
                double result = calculate(firstOperand, secondOperand, operator);

                String expressionText = tvExpression.getText().toString()+currentInput+" =";
                tvExpression.setText(expressionText);

                currentInput = String.valueOf(result);
                displayResult(Double.parseDouble(currentInput));
                operator="";
            }
        });

        Button btnSquareRoot = findViewById(R.id.btnSquareRoot);
        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentInput.isEmpty()) {
                    tvDisplay.setText(R.string.enter_calc);
                    return;
                }
                double value = Double.parseDouble(currentInput);
                if (value >= 0) {
                    double result = Math.sqrt(value);
                    displayResult(result);
                    currentInput = String.valueOf(result);
                    tvExpression.setText("√(" + value + ")");
                }
                else {
                    tvDisplay.setText(R.string.error_msg);
                }
            }
        });

        Button btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length()-1);
                    tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
                }
            }
        });
        Button btnCe = findViewById(R.id.btnCe);
        btnCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currentInput = "";
                tvDisplay.setText("");
            }
        });
        // clear all
        Button btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                firstOperand = 0;
                operator = "";
                tvDisplay.setText("");
                tvExpression.setText("");
            }
        });
        Button btnChangeSign= findViewById(R.id.btnChangeSign);
        btnChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    value=-value;
                    currentInput = formatNumber(value);
                    displayResult(value);
                }
            }
        });
        Button btnPercent= findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    String originalInput = formatNumber(value);
                    value /= 100;
                    currentInput = formatNumber(value);
                    displayResult(value);
                    tvExpression.setText(tvExpression.getText().toString() + originalInput + "%");

                } else tvDisplay.setText(R.string.enter_calc);
            }
        });
    }
    void setOperator(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentInput.isEmpty() && op.equals("-")) {
                    currentInput = "-";
                    tvDisplay.setText(currentInput);
                    return;
                }
                if (!operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    firstOperand=calculate(firstOperand, secondOperand, operator);
                    displayResult(firstOperand);
                }
                else {
                    firstOperand = Double.parseDouble(currentInput);
                }
                operator = op;
                tvExpression.setText(formatNumber(firstOperand) + " " + operator + " ");
                currentInput="";
            }
        });
    }
    double calculate (double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b!=0)
                    return a/b;
            default: return b;
        }
    }
    void displayResult(double value) {
        tvDisplay.setText(formatNumber(value));
    }
    String formatNumber(double value) {
        if (value % 1 == 0) {
            return String.valueOf((int) value);
        } else {
            return String.valueOf(value);
        }
    }
}