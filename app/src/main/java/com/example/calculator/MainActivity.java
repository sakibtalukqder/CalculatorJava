package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution,result;
    MaterialButton button_C,button_opbracket,button_clBracket;
    MaterialButton button_devide,button_X,button_plus,button_minus,button_equal;
    MaterialButton  button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_AC,button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assinedID(button_C,R.id.button_C);
        assinedID(button_opbracket,R.id.button_opbracket);
        assinedID(button_clBracket,R.id.button_clBracket);
        assinedID(button_devide,R.id.button_devide);
        assinedID(button_X,R.id.button_X);
        assinedID(button_plus,R.id.button_plus);
        assinedID(button_minus,R.id.button_minus);
        assinedID(button_equal,R.id.button_equal);
        assinedID(button_0,R.id.button_0);
        assinedID(button_1,R.id.button_1);
        assinedID(button_2,R.id.button_2);
        assinedID(button_3,R.id.button_3);
        assinedID(button_4,R.id.button_4);
        assinedID(button_5,R.id.button_5);
        assinedID(button_6,R.id.button_6);
        assinedID(button_7,R.id.button_7);
        assinedID(button_8,R.id.button_8);
        assinedID(button_9,R.id.button_9);
        assinedID(button_AC,R.id.button_AC);
        assinedID(button_dot,R.id.button_dot);

    }

    void assinedID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String btnText = button.getText().toString();
        String resText = solution.getText().toString();

        if(btnText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }

        if(btnText.equals("=")){
            String getRes = solution.getText().toString();
            String Results = getResutl(getRes);
            result.setText(Results);
            return;
        }

        if(btnText.equals("C")){
            resText = resText.substring(0,resText.length()-1);
        }

        else {
            resText = resText+btnText;
        }

        solution.setText(resText);

    }

    String getResutl(String Data){
        try {

            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String FinalResult = context.evaluateString(scriptable,Data,"Javascript",1,null).toString();
            if (FinalResult.endsWith(".0")){
                FinalResult = FinalResult.replace(".0","");
            }
            return FinalResult;

        } catch (Exception e){
            return "Error";
        }
    }
}