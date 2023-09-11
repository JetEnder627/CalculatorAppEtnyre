package com.example.calculatorappetnyre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String num1Text = "___";
    private String num2Text = "___";
    private double num1 = 0;
    private double num2 = 0;
    private String operation = "none";
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

        /*if(num1 != 0){
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
        }*/

        String outputText = num1Text;
        if(operation.equals("addition")){
            outputText += " + ";
        }else if(operation.equals("subtraction")){
            outputText += " - ";
        }else if(operation.equals("multiplication")){
            outputText += " * ";
        }else if(operation.equals("division")){
            outputText += " / ";
        }else if(operation.equals("exponent")){
            outputText += " ^ ";
        }else{
            outputText += " _ ";
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

        if(num1Text.equals("___") && editingNum1){
            num1Text = ((Integer) (int) digit).toString();
        }else if(!num1Text.equals("___") && editingNum1){
            num1Text += (int) digit;
        }else if(num2Text.equals("___") && !editingNum1){
            num2Text = ((Integer) (int) digit).toString();
        }else if(!num2Text.equals("___") && !editingNum1){
            num2Text += (int) digit;
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
            }else if(v.getId() == R.id.exponent){
                operation = "exponent";
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
        }else if(operation.equals("exponent")){
            answer = Math.pow(num1,num2);
        }
        Log.i("joey", ((Double) answer).toString());

        answerText = ((Double) answer).toString();
        updateOutput();
    }

    public void addDecimal(View v){
        if(editingNum1){
            num1IncludesDecimal = true;
            num1Text += ".";
        }else{
            num2IncludesDecimal = true;
            num2Text += ".";
        }
        if(decimalDigits == 0){
            decimalDigits = 1;
        }
        updateOutput();
    }

    public void delete(View v){
        if(editingNum1 && !num1IncludesDecimal){
            num1 = (int) (num1/10);
        }else if(editingNum1 && num1IncludesDecimal){
            for(int i = 0; i < decimalDigits; i++){
                num1 *= 10;
            }
            num1 = (int) num1/10;
            num1 = (double) num1;
            decimalDigits--;
            for(int i = 0; i < decimalDigits; i++){
                num1 /= 10;
            }
        }

        updateOutput();
    }

    public void clear(View v){
        num1Text = "___";
        num2Text = "___";
        num1 = 0;
        num2 = 0;
        decimalDigits = 0;
        operation = "none";
        answerText = "___";
        answer = 0;
        editingNum1 = true;
        operationSelected = false;
        num1IncludesDecimal = false;
        num2IncludesDecimal = false;
        updateOutput();
    }

    public void trigScreen(View v){
        Intent intent = new Intent(this, TrigActivity.class);
        startActivity(intent);
    }
}