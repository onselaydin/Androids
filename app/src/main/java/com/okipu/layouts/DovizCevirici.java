package com.okipu.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DovizCevirici extends AppCompatActivity {

    TextView tryText;
    TextView cadText;
    TextView usdText;
    TextView jpyText;
    TextView chfText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doviz_cevirici);

        tryText = findViewById(R.id.tryText);
        cadText = findViewById(R.id.cadText);
        usdText = findViewById(R.id.usdText);
        jpyText = findViewById(R.id.jpyText);
        chfText = findViewById(R.id.chfText);

    }

    public void GetRates(View view){
        DownloadData downloadData = new DownloadData();
        try
        {
            String url = "http://data.fixer.io/api/latest?access_key=d8e8f4db5f1b00c1ba864d59a0b18790";
            downloadData.execute(url);
        }
        catch (Exception e){

        }

    }

    private class DownloadData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try{

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                while (data > 0){
                    char character = (char) data;
                    result += character;
                    data = inputStreamReader.read();
                }
                return result;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "Problem" + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           // System.out.println("alınan değer:" + s);
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                //System.out.println("base:" + base);

                String rates = jsonObject.getString("rates");
                //System.out.println("rates: " + rates);

                //System.out.println("TL: " + turstring);

                JSONObject turkishjson = new JSONObject(rates);
                String turkstring = turkishjson.getString("TRY");
                tryText.setText("TRY: " + turkstring);

                String cadstring = turkishjson.getString("CAD");
                cadText.setText("CAD: " + cadstring);

                String usdstring = turkishjson.getString("USD");
                usdText.setText("USD: " + usdstring);

                String jpystring = turkishjson.getString("JPY");
                jpyText.setText("JPY: " + jpystring);

                String chfstring = turkishjson.getString("CHF");
                chfText.setText("CHF: " + chfstring);

            }
            catch (Exception e){

            }
        }
    }
}
