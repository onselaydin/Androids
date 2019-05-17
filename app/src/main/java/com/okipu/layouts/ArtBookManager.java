package com.okipu.layouts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ArtBookManager extends AppCompatActivity {

    //https://www.sketch.com/

    EditText editText;
    ImageView imageView;
    Button button;
    static SQLiteDatabase database;
    Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_book_manager);

        editText = findViewById(R.id.txtArtBook);
        imageView = findViewById(R.id.imgSelect);
        button = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        /*
        if(info.equalsIgnoreCase("info"))
        {
            Bitmap backgroud = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.ic_launcher_background);
            imageView.setImageBitmap(backgroud);

            button.setVisibility(View.VISIBLE);
            editText.setText("");

        } else {
            button.setVisibility(View.INVISIBLE);
        }
        */

        String name = intent.getStringExtra("name");
        editText.setText(name);

        int position = intent.getIntExtra("position",0);
        imageView.setImageBitmap(ArtBook.artImage.get(position));

    }

    public void save(View view){
        String artName = editText.getText().toString();

        // alttaki iki satır ile seçilen resmi sıkıştırdık.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray = outputStream.toByteArray();

        try{
            database = openOrCreateDatabase("Arts", MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS arts(name VARCHAR, image BLOB)");
            String sqlString = "INSERT INTO arts (name,image) VALUES (?, ?)";
            SQLiteStatement statement = database.compileStatement(sqlString);
            statement.bindString(1,artName);
            statement.bindBlob(2,byteArray);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
            Intent intent = new Intent(getApplicationContext(),ArtBook.class);
            startActivity(intent);
    }


    //Kullanıcıdan izin isteme yönetmeri.
    //@TargetApi(Build.VERSION_CODES.M)
    public void select(View view){


        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            // eğer kullanıcı izin vermediyse
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }
        else{
            //izin varsa aşağıdaki işlemi yapar.
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 2)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data!= null){
            Uri image = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
                imageView.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
