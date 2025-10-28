package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    String currentInput="";
    double firstOperand=0;
    String operator="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDisplay=findViewById(R.id.tvDisplay);
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
                double value = Double.parseDouble(currentInput);
                tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value));
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
                    currentInput += ".";
                    double value = Double.parseDouble(currentInput);
                    tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value));
                }
            }
        });

        Button btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty())
                {
                    firstOperand=Double.parseDouble(currentInput);
                    operator="+";
                    currentInput="";
                }
            }
        });
        Button btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty())
                {
                    firstOperand=Double.parseDouble(currentInput);
                    operator="-";
                    currentInput="";
                }
                else tvDisplay.setText("-");
            }
        });
        Button btnDivision = findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty())
                {
                    firstOperand=Double.parseDouble(currentInput);
                    operator="/";
                    currentInput="";
                }
            }
        });
        Button btnMulti = findViewById(R.id.btnMulti);
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty())
                {
                    firstOperand=Double.parseDouble(currentInput);
                    operator="*";
                    currentInput="";
                }
            }
        });

        Button btnSquareRoot = findViewById(R.id.btnSquareRoot);
        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(currentInput);
                if (value >= 0) {
                    double result = Math.sqrt(value);
                    currentInput = String.valueOf(result);
                    tvDisplay.setText(result % 1 == 0 ? String.valueOf((int)result) : String.valueOf(result));
                }
                else tvDisplay.setText(R.string.error_msg);
            }
        });
        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+": result = firstOperand + secondOperand; break;
                    case "-": result = firstOperand - secondOperand; break;
                    case "*": result = firstOperand * secondOperand; break;
                    case "/":
                        if (secondOperand != 0)
                            result = firstOperand / secondOperand;
                        else {
                            tvDisplay.setText(R.string.error_msg);
                            return;
                        }
                }
                currentInput = String.valueOf(result);
                double value = Double.parseDouble(currentInput);
                tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value));
            }
        });
        Button btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length()-1);
                    double value = Double.parseDouble(currentInput);
                    tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value));
                }
            }
        });
        // zr ta ce
        Button btnCe = findViewById(R.id.btnCe);
        btnCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                tvDisplay.setText("");
            }
        });
        Button btnChangeSign= findViewById(R.id.btnChangeSign);
        btnChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    value=-value;
                    currentInput = String.valueOf(value);
                    tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value));
                }
            }
        });
        Button btnPercent= findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    if (value != 0) {
                        value/=100;
                        currentInput = String.valueOf(value);
                        tvDisplay.setText(value % 1 == 0 ? String.valueOf((int)value) : String.valueOf(value + "%"));                    }
                }

            }
        });


    }
}