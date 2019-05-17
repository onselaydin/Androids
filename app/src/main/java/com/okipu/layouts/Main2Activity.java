package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView2 = findViewById(R.id.textView2);

        Intent intent= getIntent();
        String yourage = intent.getStringExtra("age");
        textView2.setText(yourage);
    }


    private void anaSayfa(View view){
        Intent intent = new Intent(Main2Activity.this,Main2Activity.class);

        startActivity(intent);
    }
}
