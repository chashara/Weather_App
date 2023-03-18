package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    private RequestQueue queue1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        queue1= Volley.newRequestQueue(this);
    }

    public void openMainActivity(View view) {

        Intent openMain= new Intent(this, MainActivity.class);
        startActivity(openMain);
    }

    public void fetchWeatherData2(View view) {

        String url2 = "https://api.openweathermap.org/data/2.5/weather?q=Tampere&units=metric&appid=6c433438776b5be4ac86001dc88de74d";

// Request a string response from the provided URL.
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url2,
                response1 -> {
                    Log.e("My_Application", response1);
                    parseJsonAndUpdateUI2(response1);

                }, error -> {
            Log.e("My_Application",error.toString());
        });

        queue1.add(stringRequest1);
    }

    private void parseJsonAndUpdateUI2(String response2) {

        try {
            JSONObject weatherResponse2= new JSONObject(response2);

            double temperature2;
            String description2;
            double feelLike2;
            double minTemp2;
            double maxTemp2;
            double pressure2;
            double humidity2;
            double windSpeed2;
            double windGust2;

            temperature2= weatherResponse2.getJSONObject("main").getDouble("temp");
            feelLike2= weatherResponse2.getJSONObject("main").getDouble("feels_like");
            minTemp2=weatherResponse2.getJSONObject("main").getDouble("temp_min");
            maxTemp2=weatherResponse2.getJSONObject("main").getDouble("temp_max");
            pressure2=weatherResponse2.getJSONObject("main").getDouble("pressure");
            humidity2=weatherResponse2.getJSONObject("main").getDouble("humidity");
            windSpeed2=weatherResponse2.getJSONObject("wind").getDouble("speed");
            windGust2=weatherResponse2.getJSONObject("wind").getDouble("gust");
            description2=weatherResponse2.getJSONArray("weather").getJSONObject(0).getString("description");

            TextView temperatureTextView2=findViewById(R.id.temperatureTextView2);
            temperatureTextView2.setText("" + temperature2 + " C");

            TextView feelLikeTextView2= findViewById(R.id.feelLikeTextView2);
            feelLikeTextView2.setText("" + feelLike2 + " C");

            TextView minTempTextView2= findViewById(R.id.minTempTextView2);
            minTempTextView2.setText("" + minTemp2 + " C");

            TextView maxTempTextView2= findViewById(R.id.maxTempTextView2);
            maxTempTextView2.setText("" + maxTemp2 + " C");

            TextView pressureTextView2= findViewById(R.id.pressureTextView2);
            pressureTextView2.setText("" + pressure2 + " pascal");

            TextView humidityTextView2= findViewById(R.id.humidityTextView2);
            humidityTextView2.setText("" + humidity2 + " g.m-3");

            TextView windSpeedTextView2= findViewById(R.id.windSpeedTextView2);
            windSpeedTextView2.setText("" + windSpeed2 + " m/s");

            TextView windGustTextView2= findViewById(R.id.windGustTextView2);
            windGustTextView2.setText("" + windGust2 + " m/s");

            TextView descriptionTextView2= findViewById(R.id.descriptionTextView2);
            descriptionTextView2.setText(description2);



        } catch (JSONException f) {
            //throw new RuntimeException(f);
            f.printStackTrace();
        }


    }








        }