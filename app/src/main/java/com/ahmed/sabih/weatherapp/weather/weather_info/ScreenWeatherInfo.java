package com.ahmed.sabih.weatherapp.weather.weather_info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.sabih.weatherapp.R;

public class ScreenWeatherInfo extends Fragment {


    public ScreenWeatherInfo() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.screen_weather_info, container, false);
    }

}
