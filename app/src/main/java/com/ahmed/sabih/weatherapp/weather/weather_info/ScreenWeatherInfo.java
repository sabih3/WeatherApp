package com.ahmed.sabih.weatherapp.weather.weather_info;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.core.UIUtils;
import com.ahmed.sabih.weatherapp.weather.Constants;
import com.ahmed.sabih.weatherapp.weather.ForecastAdapter;
import com.ahmed.sabih.weatherapp.weather.data_source.WeatherDataSourceImpl;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import github.vatsal.easyweather.Helper.TempUnitConverter;
import github.vatsal.easyweather.WeatherMap;
import github.vatsal.easyweather.retrofit.models.List;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;

public class ScreenWeatherInfo extends Fragment implements WeatherInfoContract.View{


    public ScreenWeatherInfo() {

    }
    @BindView(R.id.screen_weather_parent)CoordinatorLayout parentCoordinator;
    @BindView(R.id.tv_weather_city)TextView tvCityName;
    @BindView(R.id.tv_weather_desc)TextView tvWeatherDesc;
    @BindView(R.id.tv_weather_temp)TextView tvWeatherTemp;
    @BindView(R.id.tv_value_pressure) TextView tvValuePressure;
    @BindView(R.id.tv_value_humidity) TextView tvValueHumidity;
    @BindView(R.id.tv_value_wind)TextView tvValueWind;
    @BindView(R.id.iv_weather_icon)ImageView weatherIcon;
    @BindView(R.id.rv_forecast)RecyclerView forecastListView;
    @BindView(R.id.ptr_weather)SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tv_convert_celcius)TextView tvCelcius;
    @BindView(R.id.tv_convert_fah)TextView tvFahrenheit;
    @BindView(R.id.tv_separator_pipe)TextView tvSeparator;
    @BindView(R.id.misc_info_container)RelativeLayout miscInfoLayout;

    @BindString(R.string.exception_weather_info) String EXCEPTION_WEATHER_INFO;
    @BindString(R.string.exception_forecast_info)String EXCEPTION_FORECAST_INFO;
    @BindString(R.string.btn_retry) String btnRetry;

    private WeatherInfoPresenter weatherInfoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_weather_info, container, false);

        ButterKnife.bind(this,rootView);

        weatherInfoPresenter = new WeatherInfoPresenter(this,
                                                        new WeatherDataSourceImpl(getContext()));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshListener());
        weatherInfoPresenter.getWeather();
        weatherInfoPresenter.getForecast();

        return rootView;
    }

    //WeatherInfoContract.View
    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    //WeatherInfoContract.View
    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    //WeatherInfoContract.View
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        weatherInfoPresenter.onDestroy();
    }

    //WeatherInfoContract.View
    @Override
    public void setWeather(WeatherResponseModel weatherResponseModel) {
        toggleConversionPanelVisibility(true);

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

    //WeatherInfoContract.View
    @Override
    public void onWeatherFetchError(String error) {
        toggleConversionPanelVisibility(false);

        UIUtils.showSnackBar(parentCoordinator, EXCEPTION_WEATHER_INFO, btnRetry,
                new UIUtils.SnackBarActionListener() {
            @Override
            public void onSnackBarAction() {
                weatherInfoPresenter.getWeather();
            }
        });
    }

    //WeatherInfoContract.View
    @Override
    public void setForecast(ArrayList<List> forecastDataList) {
                ForecastAdapter forecastAdapter = new ForecastAdapter(forecastDataList);

                forecastListView.setLayoutManager(new LinearLayoutManager(getContext(),
                                                 LinearLayoutManager.HORIZONTAL,false));
                forecastListView.setAdapter(forecastAdapter);


    }

    //WeatherInfoContract.View
    @Override
    public void onForecastFetchError(String error) {
        UIUtils.showSnackBar(parentCoordinator, EXCEPTION_FORECAST_INFO, btnRetry,
                new UIUtils.SnackBarActionListener() {
            @Override
            public void onSnackBarAction() {
                weatherInfoPresenter.getForecast();
            }
        });
    }

    //==============================Private Methods & Inner Classes============================

//    private toggleDerivatesVisibility(boolean makeVisible){
//
//    }
    private void toggleConversionPanelVisibility(boolean makeVisible) {
        if(makeVisible){
            tvCelcius.setVisibility(View.VISIBLE);
            tvSeparator.setVisibility(View.VISIBLE);
            tvFahrenheit.setVisibility(View.VISIBLE);
            miscInfoLayout.setVisibility(View.VISIBLE);

        }else{
            tvCelcius.setVisibility(View.INVISIBLE);
            tvSeparator.setVisibility(View.INVISIBLE);
            tvFahrenheit.setVisibility(View.INVISIBLE);
            miscInfoLayout.setVisibility(View.INVISIBLE);
        }


    }
    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            weatherInfoPresenter.getWeather();
            weatherInfoPresenter.getForecast();
        }
    }
}
