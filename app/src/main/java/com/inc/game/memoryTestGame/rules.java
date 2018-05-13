package com.inc.game.memoryTestGame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class rules extends Activity {
    public TextView text;

    public TextToSpeech tellMeRules;

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rules);

        try {
            AssetFileDescriptor wonMusic = getAssets().openFd("won_bgm.wav");
            global_var.wonBgm = new MediaPlayer();
            global_var.wonBgm.setDataSource(wonMusic.getFileDescriptor(), wonMusic.getStartOffset(), wonMusic.getLength());
            global_var.wonBgm.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            AssetFileDescriptor loseMusic = getAssets().openFd("lose_bgm.wav");
            global_var.loseBgm = new MediaPlayer();
            global_var.loseBgm.setDataSource(loseMusic.getFileDescriptor(), loseMusic.getStartOffset(), loseMusic.getLength());
            global_var.loseBgm.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            AssetFileDescriptor timeupMusic = getAssets().openFd("timeup_bgm.wav");
            global_var.timeupBgm = new MediaPlayer();
            global_var.timeupBgm.setDataSource(timeupMusic.getFileDescriptor(), timeupMusic.getStartOffset(), timeupMusic.getLength());
            global_var.timeupBgm.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }

        Intent intent = getIntent();
        level = intent.getIntExtra("level_pass",0);

        text = (TextView) this.findViewById(R.id.rulesDisplay);
        final String currentRules = "Hi, Welcome to Level " + String.valueOf(level)+"\nHere you have given "+String.valueOf(level*5)+" words, in which each words have "+String.valueOf(level*5)+" points. You have "+String.valueOf(level*10)+" seconds to remember those words. \n\nTo clear this level you need to correct at least "+String.valueOf((level*5)/2)+" words.";
        text.setText(currentRules);

        tellMeRules =new TextToSpeech(rules.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    tellMeRules.setLanguage(Locale.ENGLISH);
                }else {
                    Toast.makeText(rules.this, "language not supported !", Toast.LENGTH_SHORT).show();
                }
                tellMeRules.speak(currentRules, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        if(tellMeRules != null){
            tellMeRules.shutdown();
        }
    }

    public void delayRun() {
        Intent intent = new Intent(this, wordlist.class);
        intent.putExtra("level_pass", level);
        startActivity(intent);
    }
    public void goAheadButtonFunction(View view){
        global_var.btnBgm.start();
        Button button = (Button) findViewById(R.id.goAheadBtn);
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
}
