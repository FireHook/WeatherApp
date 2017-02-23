package com.firehook.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import data.Channel;
import data.Item;
import service.WethaerServiceCallback;
import service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WethaerServiceCallback {

    private ImageView weatherIcon;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon = (ImageView) findViewById(R.id.imgView_weatherIcon);
        temperatureTextView = (TextView) findViewById(R.id.txtView_Temperature);
        conditionTextView = (TextView) findViewById(R.id.txtView_Condition);
        locationTextView = (TextView) findViewById(R.id.txtView_Location);

        service = new YahooWeatherService(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        service.refreshWeather("Warsaw, Poland");

    }

    @Override
    public void serviceSuccess(Channel channel) {
        progressDialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

        weatherIcon.setImageDrawable(weatherIconDrawble);

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        progressDialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
