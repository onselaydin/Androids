package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LandMarkBook extends AppCompatActivity {

    ListView listView;
    static Bitmap selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_book);


        listView = findViewById(R.id.listView);
        final ArrayList<String> landmarNames = new ArrayList();
        landmarNames.add("Pisa");
        landmarNames.add("Collosium");
        landmarNames.add("Eiffel");
        landmarNames.add("London Bridge");

        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisatower);
        Bitmap coll = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.colosseum);
        Bitmap eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eiffel);
        Bitmap london = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.londonbridge);


        final ArrayList<Bitmap> landmarkImages = new ArrayList();
        landmarkImages.add(pisa);
        landmarkImages.add(coll);
        landmarkImages.add(eiffel);
        landmarkImages.add(london);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarNames);
        listView.setAdapter(aa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("name",landmarNames.get(position));

                selectedImage = landmarkImages.get(position);
                startActivity(intent);

            }
        });

    }
}
