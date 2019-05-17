package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ArtBook extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase database;
    static ArrayList<Bitmap> artImage;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.add_art){
            Intent intent = new Intent(getApplicationContext(), ArtBookManager.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_book);

        listView = findViewById(R.id.artList);

        final ArrayList<String> artName = new ArrayList();
        artImage = new ArrayList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, artName);
        listView.setAdapter(arrayAdapter);

        try{

            ArtBookManager.database = openOrCreateDatabase("Arts", MODE_PRIVATE,null);
            ArtBookManager.database.execSQL("CREATE TABLE IF NOT EXISTS arts(name VARCHAR, image BLOB)");

            Cursor cursor =  ArtBookManager.database.rawQuery("SELECT * FROM arts",null);
            int nameIx = cursor.getColumnIndex("name");
            int imageIx = cursor.getColumnIndex("image");
            cursor.moveToFirst();

            while(cursor != null){
                artName.add(cursor.getString(nameIx));

                byte[] byteArray = cursor.getBlob(imageIx);
                Bitmap image = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                artImage.add(image);
                cursor.moveToNext();

                arrayAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArtBookManager.class);
                intent.putExtra("info","old");
                intent.putExtra("name",artName.get(position));
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

    }
}
