package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imageView10);
        textView = findViewById(R.id.textView4);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        textView.setText(name);
        imageView.setImageBitmap(LandMarkBook.selectedImage);
    }
}
