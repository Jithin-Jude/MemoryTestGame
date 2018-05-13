package com.inc.game.memoryTestGame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class result extends AppCompatActivity {

    public TextView givenWrdsTextResult,correctWrdsTextResult,wrongWrdsTextResult,givenWrdsTexTitle,correctWrdsTextTitle,wrongWrdsTextTitle;
    String correctWrds="",wrongWrds="";
    int numCorrectWrds=0,numWrongWrds=0;
    int level,numWrds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        String userEnterednWrds = intent.getStringExtra("userInput");
        String[] userEnterednWrdsArray = userEnterednWrds.split(" ");

        String givenWrds_display = intent.getStringExtra("givenWrds_display");
        String[] givenWrds_displayArray = givenWrds_display.split("\n");
        String[] givenWrds_copy = Arrays.copyOf(givenWrds_displayArray, givenWrds_displayArray.length);

        level = intent.getIntExtra("level_pass",0);
        numWrds = level*5;

        int numUserEnterednWrds = userEnterednWrdsArray.length;
        int numGivenWords = givenWrds_displayArray.length;
        int index;
        for(int i=0;i<numUserEnterednWrds;++i) {
            if (Arrays.asList(givenWrds_copy).contains(userEnterednWrdsArray[i])){
                index = Arrays.asList(givenWrds_copy).indexOf(userEnterednWrdsArray[i]);
                givenWrds_copy[index]= null;
                correctWrds += userEnterednWrdsArray[i];
                correctWrds +="\n";
                ++numCorrectWrds;
            }
            else{
                if(userEnterednWrdsArray[i] != ""){
                wrongWrds += userEnterednWrdsArray[i];
                wrongWrds +="\n";
                ++numWrongWrds;
                }
            }
        }

        givenWrdsTexTitle = (TextView) this.findViewById(R.id.givenWrds_title);
        givenWrdsTexTitle.setText("Given Words : "+String.valueOf(numWrds));

        givenWrdsTextResult = (TextView) this.findViewById(R.id.givenWrds_result);
        givenWrdsTextResult.setText(givenWrds_display);

        correctWrdsTextTitle = (TextView) this.findViewById(R.id.correctWrds_title);
        correctWrdsTextTitle.setText("Correct Words : "+String.valueOf(numCorrectWrds));

        correctWrdsTextResult = (TextView) this.findViewById(R.id.correctWrds_result);
        correctWrdsTextResult.setText(correctWrds);


        wrongWrdsTextTitle = (TextView) this.findViewById(R.id.wrongWrds_title);
        wrongWrdsTextTitle.setText("Wrong/Repeated Words : "+String.valueOf(numWrongWrds));


        wrongWrdsTextResult = (TextView) this.findViewById(R.id.wrongWrds_result);
        wrongWrdsTextResult.setText(wrongWrds);
    }
    public void delayRun() {
        if(numCorrectWrds >= numWrds/2) {
            Intent intent = new Intent(this, rewards_won.class);
            intent.putExtra("level_pass",level);
            intent.putExtra("numCorrectWrds",numCorrectWrds);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, rewards_lose.class);
            intent.putExtra("level_pass",level);
            intent.putExtra("numCorrectWrds",numCorrectWrds);
            startActivity(intent);
        }
    }
    public void Your_Status_ButtonFunction(View view){
        global_var.btnBgm.start();
        Button button = (Button) findViewById(R.id.Your_Status_Btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.animator.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        bounceInterpolator interpolator = new bounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                delayRun();
            }
        }, 100);
    }
    @Override
    public void onBackPressed() {}
}
