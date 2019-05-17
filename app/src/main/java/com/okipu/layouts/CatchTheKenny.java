package com.okipu.layouts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.RunnableScheduledFuture;

public class CatchTheKenny extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    int score;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_the_kenny);

        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImage();
        score = 0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Zaman doldu");
                handler.removeCallbacks(runnable);

                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(CatchTheKenny.this);
                alert.setTitle("yeniden başlat");
                alert.setMessage("Tekrar oynarmısın...");

                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Formu baştan yükler...
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CatchTheKenny.this,"Oyun Bitti", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void hideImage(){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1000);

            }
        };

        handler.post(runnable);
    }

    public void increaseScore(View view){
        score++;

        scoreText.setText("Score: " + score);
    }
}
