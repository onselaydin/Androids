package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Sayac extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayac);

        textView = findViewById(R.id.textView3);

        new CountDownTimer(10000,1000){

            //her saniye ne yapayım
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Left: " + millisUntilFinished / 1000);
            }

            //Bitince ne yapayım
            @Override
            public void onFinish() {

            }
        }.start();
    }
}
