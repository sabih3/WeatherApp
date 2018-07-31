package com.ahmed.sabih.weatherapp.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ahmed.sabih.weatherapp.core.adapters.FragmentAdapter;
import com.ahmed.sabih.weatherapp.model.FragmentHolder;
import com.ahmed.sabih.weatherapp.restaurants.restaurant_list.ScreenRestaurantList;
import com.ahmed.sabih.weatherapp.weather.weather_info.ScreenWeatherInfo;

import java.util.ArrayList;

public class ViewPagerHelper {

    public FragmentAdapter getViewPagerAdapter(FragmentManager supportFragmentManager){
        Fragment weatherScreen = new ScreenWeatherInfo();
        Fragment restaurantListScreen = new ScreenRestaurantList();

        FragmentHolder weatherFragment = new FragmentHolder(weatherScreen,"Weather");
        FragmentHolder restaurantFragment = new FragmentHolder(restaurantListScreen,"Restaurants Near by ");

        ArrayList<FragmentHolder> fragmentList = new ArrayList<>();
        fragmentList.add(weatherFragment);
        fragmentList.add(restaurantFragment);

        FragmentAdapter adapter = new FragmentAdapter(supportFragmentManager,fragmentList);

        return adapter;
    }

}
