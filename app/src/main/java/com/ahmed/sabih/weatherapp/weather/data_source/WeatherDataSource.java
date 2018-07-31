package com.ahmed.sabih.weatherapp.weather.data_source;

public interface WeatherDataSource {

    void getWeather(String lat, String lon);

    void getForecast(String lat, String lon);

}
