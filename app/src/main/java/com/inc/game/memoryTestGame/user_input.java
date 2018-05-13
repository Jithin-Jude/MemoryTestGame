package com.inc.game.memoryTestGame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class user_input extends AppCompatActivity {
    String givenWrds_display = null;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_input);

        Intent intent = getIntent();
        givenWrds_display = intent.getStringExtra("givenWrds_display");
        level = intent.getIntExtra("level_pass",0);


    }
    public void delayRun() {
        final EditText simpleEditText = (EditText) findViewById(R.id.userInput);
        String userInput = simpleEditText.getText().toString();
        userInput.replace('\n', ' ');
        Intent intent = new Intent(this, result.class);
        intent.putExtra("userInput",userInput);
        intent.putExtra("givenWrds_display",givenWrds_display);
        intent.putExtra("level_pass",level);
        startActivity(intent);
    }
    public void submittButtonFunction(View view){
        global_var.btnBgm.start();
        Button button = (Button) findViewById(R.id.submittBtn);
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
