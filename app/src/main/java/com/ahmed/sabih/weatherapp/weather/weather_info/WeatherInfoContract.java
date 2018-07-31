package com.ahmed.sabih.weatherapp.weather.weather_info;

import java.util.ArrayList;

import github.vatsal.easyweather.retrofit.models.ForecastResponseModel;
import github.vatsal.easyweather.retrofit.models.List;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;

public interface WeatherInfoContract {

    interface View{

        void showProgress();
        void hideProgress();
        void setWeather(WeatherResponseModel weatherResponseModel);
        void onWeatherFetchError(String error);
        void setForecast(ArrayList<List> forecastResponseModel);
        void onForecastFetchError(String error);
    }

    interface Presenter{

        void getWeather();
        void getForecast();
        void onDestroy();
    }

    interface WeatherInteractor {
        void getWeather(OnWeatherFetchListener onCompleteListener);

        interface OnWeatherFetchListener {
            void onWeatherFetchedSuccess(WeatherResponseModel weatherResponseModel);

            void onWeatherFetchedFailure(String error);


        }
    }
        interface ForecastInteractor{
            void getForecast(OnForecastFetchListener onForecastFetchListener);

        interface OnForecastFetchListener{
            void onForecastFetchedSuccess(ForecastResponseModel forecastResponseModel);
            void onForecaseFetchedFailure(String error);
        }

    }





}
