package com.ahmed.sabih.weatherapp.weather.weather_info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.core.UIUtils;
import com.ahmed.sabih.weatherapp.weather.Constants;
import com.ahmed.sabih.weatherapp.weather.ForecastAdapter;
import com.ahmed.sabih.weatherapp.weather.data_source.WeatherDataSourceImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.vatsal.easyweather.Helper.TempUnitConverter;
import github.vatsal.easyweather.WeatherMap;
import github.vatsal.easyweather.retrofit.models.List;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;

public class ScreenWeatherInfo extends Fragment implements WeatherInfoContract.View{


    public ScreenWeatherInfo() {

    }

    @BindView(R.id.tv_weather_city)TextView tvCityName;
    @BindView(R.id.tv_weather_desc)TextView tvWeatherDesc;
    @BindView(R.id.tv_weather_temp)TextView tvWeatherTemp;
    @BindView(R.id.tv_convert_celcius)TextView tvConverToCelcius;
    @BindView(R.id.tv_convert_fah)TextView tvConvertToFah;
    @BindView(R.id.tv_value_pressure) TextView tvValuePressure;
    @BindView(R.id.tv_value_humidity) TextView tvValueHumidity;
    @BindView(R.id.tv_value_wind)TextView tvValueWind;
    @BindView(R.id.iv_weather_icon)ImageView weatherIcon;
    @BindView(R.id.rv_forecast)RecyclerView forecastListView;

    @BindView(R.id.ptr_weather)SwipeRefreshLayout swipeRefresh;

    private WeatherInfoPresenter weatherInfoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_weather_info, container, false);

        ButterKnife.bind(this,rootView);

        WeatherInfoPresenter weatherInfoPresenter =
                             new WeatherInfoPresenter(this,new WeatherDataSourceImpl(getContext()));

        weatherInfoPresenter.getWeather();
        weatherInfoPresenter.getForecast();
        final WeatherMap weatherMap = new WeatherMap(getContext(), "b123b656c69c29d0824736e2508416f7");

//        weatherMap.getLocationForecast("25.3463", "55.4209", new ForecastCallback() {
//            @Override
//            public void success(ForecastResponseModel forecastResponseModel) {
//
        return rootView;
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void setWeather(WeatherResponseModel weatherResponseModel) {
        tvCityName.setText(weatherResponseModel.getName());
        try {
            tvWeatherDesc.setText(weatherResponseModel.getWeather()[0].getDescription());
        }catch (Exception exc){
            tvWeatherDesc.setText(getString(R.string.exception_weather_description));
        }

        tvWeatherTemp.setText(String.valueOf(TempUnitConverter.convertToCelsius(weatherResponseModel.getMain().getTemp()).intValue()
        ));

        tvValuePressure.setText(weatherResponseModel.getMain().getPressure());
        tvValueHumidity.setText(weatherResponseModel.getMain().getHumidity());
        tvValueWind.setText(weatherResponseModel.getWind().getSpeed());

        UIUtils.showImage(getContext(),Constants.URL_OPEN_WEATHER_ICON+
                weatherResponseModel.getWeather()[0].getIcon()+".png",weatherIcon);
    }

    @Override
    public void onWeatherFetchError(String error) {

    }

    @Override
    public void setForecast(ArrayList<List> forecastDataList) {
                ForecastAdapter forecastAdapter = new ForecastAdapter(forecastDataList);

                forecastListView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                forecastListView.setAdapter(forecastAdapter);

                //TempUnitConverter.convertToCelsius(list[8].getMain().getTemp());

    }



    @Override
    public void onForecastFetchError(String error) {

    }
}
