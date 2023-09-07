package com.example.calculatorappetnyre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String num1Text = "___";
    private String num2Text = "___";
    private double num1 = 0;
    private double num2 = 0;
    private String operation = "addition";
    private String answerText = "___";
    private double answer = 0;
    private boolean editingNum1 = true;
    private boolean operationSelected = false;
    private boolean num1IncludesDecimal = false;
    private boolean num2IncludesDecimal = false;
    private int decimalDigits = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateOutput();
    }

    public void updateOutput(){
        TextView outputTV = findViewById(R.id.outputScreen);

        if(num1 != 0){
            if(!num1IncludesDecimal){
                num1Text = ((Integer) ((int) num1)).toString();
            }else{
                num1Text = ((Double) num1).toString();
            }
        }else{
            num1Text = "___";
        }

        if(num2 != 0){
            if(!num2IncludesDecimal){
                num2Text = ((Integer) ((int) num2)).toString();
            }else{
                num2Text = ((Double) num2).toString();
            }
        }else{
            num2Text = "___";
        }

        String outputText = num1Text;
        if(operation.equals("addition")){
            outputText += " + ";
        }else if(operation.equals("subtraction")){
            outputText += " - ";
        }else if(operation.equals("multiplication")){
            outputText += " * ";
        }else if(operation.equals("division")){
            outputText += " / ";
        }
        outputText += num2Text + " =\n" + answerText;

        outputTV.setText(outputText);
    }

    public void numInput(View v){
        double num;
        if(editingNum1){
            num = num1;
        }else{
            num = num2;
        }

        double digit = 0;

        if(v.getId() == R.id.num1){
            digit = 1;
        }else if(v.getId() == R.id.num2){
            digit = 2;
        }else if(v.getId() == R.id.num3){
            digit = 3;
        }else if(v.getId() == R.id.num4){
            digit = 4;
        }else if(v.getId() == R.id.num5){
            digit = 5;
        }else if(v.getId() == R.id.num6){
            digit = 6;
        }else if(v.getId() == R.id.num7){
            digit = 7;
        }else if(v.getId() == R.id.num8){
            digit = 8;
        }else if(v.getId() == R.id.num9){
            digit = 9;
        }else if(v.getId() == R.id.num0){
            digit = 0;
        }

        if((editingNum1 && !num1IncludesDecimal) || (!editingNum1 && !num2IncludesDecimal)){
            num *= 10;
            num += digit;
        }else{
            for(int i = 0; i < decimalDigits; i++){
                digit /= 10;
            }
            num += digit;
            decimalDigits += 1;
        }

        if(editingNum1){
            num1 = num;
        }else{
            num2 = num;
        }

        updateOutput();
    }

    public void selectOperation(View v){
        if(!operationSelected){
            if(v.getId() == R.id.divide){
                operation = "division";
            }else if(v.getId() == R.id.multiply){
                operation = "multiplication";
            }else if(v.getId() == R.id.subtract){
                operation = "subtraction";
            }else if(v.getId() == R.id.add){
                operation = "addition";
            }
            updateOutput();
            operationSelected = true;
            editingNum1 = false;
            decimalDigits = 0;
        }
    }

    public void runOperation(View v){
        if(operation.equals("division")){
            answer = num1/num2;
        }else if(operation.equals("multiplication")){
            answer = num1*num2;
        }else if(operation.equals("subtraction")){
            answer = num1-num2;
        }else if(operation.equals("addition")){
            answer = num1+num2;
        }

        answerText = ((Double) answer).toString();
        updateOutput();
    }

    public void addDecimal(View v){
        if(editingNum1){
            num1IncludesDecimal = true;
        }else{
            num2IncludesDecimal = true;
        }
        if(decimalDigits == 0){
            decimalDigits = 1;
        }
    }

    public void delete(View v){
        if(editingNum1 && !num1IncludesDecimal){
            num1 = (int) (num1/10);
        }else if(editingNum1 && num1IncludesDecimal){
            for(int i = 0; i < decimalDigits - 1; i++){
                num1 *= 10;
            }
            num1 = (int) num1;
            num1 = (double) num1;
            for(int i = 0; i < decimalDigits - 1; i++){
                num1 /= 10;
            }
        }

        updateOutput();
    }
}