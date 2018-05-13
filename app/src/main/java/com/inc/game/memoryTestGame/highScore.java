package com.inc.game.memoryTestGame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class highScore extends AppCompatActivity {
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_high_score);

        //  DATABASE STUFF BEGINS

        String file = "high_score.txt", textContent = "0", textData = "0";

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

        //  DATABASE STUFF OVER

        text = (TextView) this.findViewById(R.id.score);
        text.setText(textContent);
    }
}
