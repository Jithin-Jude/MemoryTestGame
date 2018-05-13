package com.inc.game.memoryTestGame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class splashscreen extends Activity {

    String file = "high_score.txt", textContent = "0", textData = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{


                    try{
                        FileInputStream fis = openFileInput(file);
                        int size = fis.available();
                        byte[] buffer = new byte[size];
                        fis.read(buffer);
                        fis.close();
                        textContent = new String(buffer);

                    }catch (Exception e1) {
                        e1.printStackTrace();

                        try{
                            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
                            fos.write(textData.getBytes());
                            fos.close();
                        } catch (Exception e2){
                            e2.printStackTrace();
                        }
                    }

                    int checkFirstTime = Integer.valueOf(textContent);
                    if(checkFirstTime == 0){
                        Intent intent = new Intent(splashscreen.this,Tutorial.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(splashscreen.this,fww.class);
                        startActivity(intent);
                    }


                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    @Override
    public void onBackPressed() {}

}