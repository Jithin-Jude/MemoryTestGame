package com.inc.game.memoryTestGame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class fww extends AppCompatActivity {

    TextView motoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fww);

        motoText = (TextView) this.findViewById(R.id.moto);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the time
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        motoText.startAnimation(anim);


        try {
            AssetFileDescriptor btnMusic = getAssets().openFd("btn_bgm.wav");
            global_var.btnBgm = new MediaPlayer();
            global_var.btnBgm.setDataSource(btnMusic.getFileDescriptor(), btnMusic.getStartOffset(), btnMusic.getLength());
            global_var.btnBgm.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void delayRun4() {
        Intent intent = new Intent(this, highScore.class);
        startActivity(intent);
    }

    public void highScoreButtonFunction(View view) {
        global_var.btnBgm.start();
        Button button = (Button)findViewById(R.id.high_score_btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.animator.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        bounceInterpolator interpolator = new bounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                delayRun4();
            }
        }, 100);
    }

    public void delayRun3() {
        Intent intent = new Intent(this, rules.class);
        int level=1;
        global_var.tot_pts = 0;
        intent.putExtra("level_pass",level);
        startActivity(intent);
    }

    public void playButtonFunction(View view) {
        global_var.btnBgm.start();
        Button button = (Button)findViewById(R.id.play_btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.animator.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        bounceInterpolator interpolator = new bounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                delayRun3();
            }
        }, 100);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    }
