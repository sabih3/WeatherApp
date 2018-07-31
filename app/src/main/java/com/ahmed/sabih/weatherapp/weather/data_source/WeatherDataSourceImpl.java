package com.ahmed.sabih.weatherapp.weather.data_source;

import android.content.Context;

import com.ahmed.sabih.weatherapp.core.UserManager;
import com.ahmed.sabih.weatherapp.weather.weather_info.WeatherInfoContract;

import github.vatsal.easyweather.Helper.ForecastCallback;
import github.vatsal.easyweather.Helper.WeatherCallback;
import github.vatsal.easyweather.WeatherMap;
import github.vatsal.easyweather.retrofit.models.ForecastResponseModel;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;

import static com.ahmed.sabih.weatherapp.weather.Constants.WEATHER_APP_ID;

public class WeatherDataSourceImpl implements WeatherInfoContract.WeatherInteractor,
                                              WeatherInfoContract.ForecastInteractor{

    private Context context;

    public WeatherDataSourceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getWeather(final OnWeatherFetchListener onCompleteListener) {
        final WeatherMap weatherMap = new WeatherMap(context, WEATHER_APP_ID);
        weatherMap.getLocationWeather(UserManager.getInstance().getUserLat(),
                                    UserManager.getInstance().getUserLon(), new WeatherCallback() {
            @Override
            public void success(WeatherResponseModel weatherResponseModel) {
                onCompleteListener.onWeatherFetchedSuccess(weatherResponseModel);
            }

            @Override
            public void failure(String error) {
                onCompleteListener.onWeatherFetchedFailure(error);
            }
        });
    }

    @Override
    public void getForecast(final OnForecastFetchListener onForecastFetchListener) {
        final WeatherMap weatherMap = new WeatherMap(context, WEATHER_APP_ID);

        weatherMap.getLocationForecast(UserManager.getInstance().getUserLat(),
                UserManager.getInstance().getUserLon(), new ForecastCallback() {
                    @Override
                    public void success(ForecastResponseModel forecastResponseModel) {
                        onForecastFetchListener.onForecastFetchedSuccess(forecastResponseModel);
                    }

                    @Override
                    public void failure(String error) {
                        onForecastFetchListener.onForecaseFetchedFailure(error);
                    }
                });
    }
}
