package com.example.calculatorappetnyre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrigActivity extends AppCompatActivity {
    private double trigNum = 0;
    private String trigNumText = "___";
    private boolean trigNumIncludesDecimal = false;
    private String operation = "";
    private double answer = 0;
    private String answerText = "___";
    private boolean operationSelected = false;
    private int decimalDigits = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trig);

        updateOutput();
    }

    public void updateOutput(){
        TextView outputTV = findViewById(R.id.trigOutputScreen);

        /*if(!trigNumIncludesDecimal){
            trigNumText = ((Integer) ((int) trigNum)).toString();
        }else{
            trigNumText = ((Double) trigNum).toString();
        }*/

        String outputText = "";
        if(operation.equals("cosine")){
            outputText += "cos(";
        }else if(operation.equals("sine")){
            outputText += "sin(";
        }else if(operation.equals("tangent")){
            outputText += "tan(";
        }else if(operation.equals("arccosine")){
            outputText += "arccos(";
        }else if(operation.equals("arcsine")){
            outputText += "arcsin(";
        }else if(operation.equals("arctangent")){
            outputText += "arctan(";
        }else{
            outputText += "Placeholder(";
        }
        outputText += trigNumText + ") =\n" + answerText;

        outputTV.setText(outputText);
    }

    public void selectOperation(View v){
        if(!operationSelected){
            if(v.getId() == R.id.cosine){
                operation = "cosine";
            }/*else if(v.getId() == R.id.sine){
                operation = "sine";
            }else if(v.getId() == R.id.tangent){
                operation = "tangent";
            }*/
            updateOutput();
            operationSelected = true;
        }
    }

    public void runOperation(View v){
        if(operation.equals("cosine")){
            answer = Math.cos(trigNum);
        }else if(operation.equals("sine")){
            answer = Math.sin(trigNum);
        }else if(operation.equals("tangent")){
            answer = Math.tan(trigNum);
        }else if(operation.equals("arccosine")){
            answer = Math.acos(trigNum);
        }else if(operation.equals("arcsine")){
            answer = Math.asin(trigNum);
        }else if(operation.equals("arctangent")){
            answer = Math.atan(trigNum);
        }

        answerText = ((Double) answer).toString();
        updateOutput();
    }

    public void numInput(View v){
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

        if(trigNumText.equals("___")){
            trigNumText = ((Integer) (int) digit).toString();
        }else if(!trigNumText.equals("___")){
            trigNumText += (int) digit;
        }

        if(!trigNumIncludesDecimal){
            trigNum *= 10;
            trigNum += digit;
        }else{
            for(int i = 0; i < decimalDigits; i++){
                digit /= 10;
            }
            trigNum += digit;
            decimalDigits += 1;
        }

        updateOutput();
    }

    public void addDecimal(View v){
        trigNumIncludesDecimal = true;
        if(decimalDigits == 0){
            decimalDigits = 1;
            trigNumText += ".";
        }
        updateOutput();
    }

    public void invertOperation(View v){
        if(operationSelected){
            if(operation.equals("cosine")){
                operation = "arccosine";
            }else if(operation.equals("sine")){
                operation = "arcsine";
            }else if(operation.equals("tangent")){
                operation = "arctangent";
            }
            updateOutput();
        }
    }

    public void goBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
