package com.okipu.layouts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //2 tip context var.
    //1. activity context  örnek  MainActivity.this
    //2. app oontext kullanmak için getApplicationContext()

    TextView lbl_Age;
    EditText txt_Age;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lbl_Age = findViewById(R.id.lblAge);
        txt_Age = findViewById(R.id.txtAge);
        sharedPreferences = this.getSharedPreferences("com.okipu.layouts", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge",0);
        lbl_Age.setText("Your Age :"+storedAge);


    }

    public void MMap(View view){
        Intent mp = new Intent(MainActivity.this, MyMap.class);
        startActivity(mp);
    }

    public void Art_Book(View view){
        Intent art = new Intent(MainActivity.this, ArtBook.class);
        startActivity(art);
    }

    public void My_Database(View view){
        Intent mydb = new Intent(MainActivity.this, MyDatabase.class);
        startActivity(mydb);
    }

    public void SayacaGit(View view){
        Intent git = new Intent(MainActivity.this,Sayac.class);
        startActivity(git);
    }

    public void Oyun(View view){
        Intent oyun = new Intent(MainActivity.this, CatchTheKenny.class);
        startActivity(oyun);
    }
    public void Lmb(View view){
        Intent lmb = new Intent(MainActivity.this, LandMarkBook.class);
        startActivity(lmb);
    }

    public void two(View view){
        Intent intent=new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("age",lbl_Age.getText().toString());
        startActivity(intent);

    }

    public void kaydet(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Kaydet");
        alert.setMessage(" Kaydetmek istermisin?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(!txt_Age.getText().toString().matches("")){
                    int userAge = Integer.parseInt(txt_Age.getText().toString());
                    lbl_Age.setText("Your Age :" + userAge);

                    sharedPreferences.edit().putInt("storedAge",userAge).apply();
                }
                Toast.makeText(MainActivity.this,"Kaydedildi.",Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Kaydedilmedi.",Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }
}
