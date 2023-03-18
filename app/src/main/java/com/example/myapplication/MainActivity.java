package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.io.StringReader;
public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue= Volley.newRequestQueue(this);
    }

    public void openForecastActivity(View view) {
        Intent openForecast = new Intent(this, MainActivity2.class);
        startActivity(openForecast);
    }

    public void fetchWeatherData(View view) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Helsinki&units=metric&appid=6c433438776b5be4ac86001dc88de74d";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("My_Application", response);
                    parseJsonAndUpdateUI(response);
                }, error -> {
            Log.d("My_Application", error.toString());
        });
        queue.add(stringRequest);
    }

    private void parseJsonAndUpdateUI(String response) {



        try {
            JSONObject weatherResponse = new JSONObject(response);
            double temperature;
            String description;
            double feelLike;
            double minTemp;
            double maxTemp;
            double pressure;
            double humidity;
            double windSpeed;
            double windGust;
            temperature = weatherResponse.getJSONObject("main").getDouble("temp");
            feelLike = weatherResponse.getJSONObject("main").getDouble("feels_like");
            minTemp = weatherResponse.getJSONObject("main").getDouble("temp_min");
            maxTemp = weatherResponse.getJSONObject("main").getDouble("temp_max");
            pressure = weatherResponse.getJSONObject("main").getDouble("pressure");
            humidity = weatherResponse.getJSONObject("main").getDouble("humidity");
            windSpeed = weatherResponse.getJSONObject("wind").getDouble("speed");
            windGust = weatherResponse.getJSONObject("wind").getDouble("gust");
            description = weatherResponse.getJSONArray("weather").getJSONObject(0).getString("description");


            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText("" + temperature + " C");

            TextView feelLikeTextView = findViewById(R.id.feelsLikeTextView);
            feelLikeTextView.setText("" + feelLike + " C");

            TextView minTempTextView = findViewById(R.id.minTempTextView);
            minTempTextView.setText("" + minTemp + " C");

            TextView maxTempTextView = findViewById(R.id.maxTempTextView);
            maxTempTextView.setText("" + maxTemp + " C");

            TextView pressureTextView = findViewById(R.id.pressureTextView);
            pressureTextView.setText("" + pressure + " pascal");

            TextView humidityTextView = findViewById(R.id.humidityTextView);
            humidityTextView.setText("" + humidity + " g.m-3");

            TextView windSpeedTextView = findViewById(R.id.windSpeedTextView);
            windSpeedTextView.setText("" + windSpeed + " m/s");

            TextView windGustTextView = findViewById(R.id.windGustTextView);
            windGustTextView.setText("" + windGust + " m/s");

            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(description);




        } catch (JSONException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();

        }

    }

}