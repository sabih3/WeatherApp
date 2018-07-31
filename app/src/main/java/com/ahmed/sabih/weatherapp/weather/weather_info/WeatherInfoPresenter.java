package com.ahmed.sabih.weatherapp.weather.weather_info;


import github.vatsal.easyweather.retrofit.models.ForecastResponseModel;
import github.vatsal.easyweather.retrofit.models.List;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;
import com.ahmed.sabih.weatherapp.weather.data_source.WeatherDataSourceImpl;

import java.util.ArrayList;

public class WeatherInfoPresenter implements WeatherInfoContract.Presenter,
                                  WeatherInfoContract.WeatherInteractor.OnWeatherFetchListener,
                                  WeatherInfoContract.ForecastInteractor.OnForecastFetchListener{

    private WeatherInfoContract.View view;
    private WeatherDataSourceImpl weatherDataSourceImpl;

    public WeatherInfoPresenter(WeatherInfoContract.View view,
                                WeatherDataSourceImpl weatherDataSource) {

        this.view = view;
        this.weatherDataSourceImpl = weatherDataSource;
    }

    @Override
    public void getWeather() {
        view.showProgress();
        weatherDataSourceImpl.getWeather(this);
    }

    @Override
    public void getForecast() {
        weatherDataSourceImpl.getForecast(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onWeatherFetchedSuccess(WeatherResponseModel weatherResponseModel) {
        if(view != null){
            view.hideProgress();
            view.setWeather(weatherResponseModel);
        }

    }

    @Override
    public void onWeatherFetchedFailure(String error) {
        if(view != null){
            view.hideProgress();
            view.onWeatherFetchError(error);
        }

    }

    @Override
    public void onForecastFetchedSuccess(ForecastResponseModel forecastResponseModel) {
        if(view != null){
            //Forecast List, gives 5 days forecast with 3 hour interval
            List[] list = forecastResponseModel.getList();
            ArrayList<List> forecastDataList = new ArrayList<>();

            //Extracting weather of 12 noon
            List list1 = list[8];
            List list2 = list[16];
            List list3 = list[24];
            List list4 = list[32];
            List list5 = list[39];

            forecastDataList.add(list1);
            forecastDataList.add(list2);
            forecastDataList.add(list3);
            forecastDataList.add(list4);
            forecastDataList.add(list5);
            view.setForecast(forecastDataList);
        }
    }

    @Override
    public void onForecaseFetchedFailure(String error) {
        view.onForecastFetchError(error);
    }
}
