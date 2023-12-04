package com.example.shouflihaldocteur;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBarcketOpen,buttonBracketClose;
    MaterialButton buttonDevide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assginId(buttonC,R.id.button_c);
        assginId(buttonBarcketOpen,R.id.button_open_bracket);
        assginId(buttonBracketClose,R.id.button_close_bracket);
        assginId(buttonDevide,R.id.button_devide);
        assginId(buttonMultiply,R.id.button_multiply);
        assginId(buttonPlus,R.id.button_plus);
        assginId(buttonMinus,R.id.button_minus);
        assginId(buttonEquals,R.id.button_equals);
        assginId(button0,R.id.button_0);
        assginId(button1,R.id.button_1);
        assginId(button2,R.id.button_2);
        assginId(button3,R.id.button_3);
        assginId(button4,R.id.button_4);
        assginId(button5,R.id.button_5);
        assginId(button6,R.id.button_6);
        assginId(button7,R.id.button_7);
        assginId(button8,R.id.button_8);
        assginId(button9,R.id.button_9);
        assginId(buttonDot,R.id.button_dot);
        assginId(buttonAc,R.id.button_ac);


    }
    public void assginId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
                    MaterialButton button = (MaterialButton) view;
                     String buttonText =  button.getText().toString();
               String dataToCalculate = solutionTv.getText().toString();
                     if (buttonText.equals("AC")){
                         solutionTv.setText("");
                         resultTv.setText("0");
                         return;
                     }
                     if (buttonText.equals("=")){
                         solutionTv.setText(resultTv.getText());
                         return;
                     }
                   if (buttonText.equals("C")){
                       dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

                        }else{
                       dataToCalculate = dataToCalculate+buttonText;

                   }
                     solutionTv.setText(dataToCalculate);
                   String finalResult = getResult(dataToCalculate);
                   if (!finalResult.equals("Error")){
                          resultTv.setText(finalResult);
                   }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace("0","");
            }

            return finalResult;
        }catch (Exception ex){
              return "Error";
        }
    }


}