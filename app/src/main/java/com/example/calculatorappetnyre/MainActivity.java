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
    private String answer = "___";
    private boolean editingNum1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateOutput();
    }

    public void updateOutput(){
        TextView outputTV = findViewById(R.id.outputScreen);

        if(num1 != 0){
            num1Text = ((Double) num1).toString();
        }else{
            num1Text = "___";
        }

        if(num2 != 0){
            num2Text = ((Double) num2).toString();
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
        outputText += num2Text + " =\n" + answer;

        outputTV.setText(outputText);
    }

    public void numInput(View v){
        double num;
        if(editingNum1){
            num = num1;
        }else{
            num = num2;
        }

        if(v.getId() == R.id.num1){
            num *= 10;
            num += 1;
        }else if(v.getId() == R.id.num2){
            num *= 10;
            num += 2;
        }else if(v.getId() == R.id.num3){
            num *= 10;
            num += 3;
        }else if(v.getId() == R.id.num4){
            num *= 10;
            num += 4;
        }else if(v.getId() == R.id.num5){
            num *= 10;
            num += 5;
        }else if(v.getId() == R.id.num6){
            num *= 10;
            num += 6;
        }else if(v.getId() == R.id.num7){
            num *= 10;
            num += 7;
        }else if(v.getId() == R.id.num8){
            num *= 10;
            num += 8;
        }else if(v.getId() == R.id.num9){
            num *= 10;
            num += 9;
        }else if(v.getId() == R.id.num0){
            num *= 10;
            num += 0;
        }

        if(editingNum1){
            num1 = num;
        }else{
            num2 = num;
        }

        updateOutput();
    }

    public void selectOperation(View v){
        if(v.getId() == R.id.divide){
            operation = "division";
        }
    }
}