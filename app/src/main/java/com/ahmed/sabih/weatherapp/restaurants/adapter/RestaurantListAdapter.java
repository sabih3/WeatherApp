package com.ahmed.sabih.weatherapp.restaurants.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestauratRowHolder>{

    private List<ResponseNearByRestaurants.NearbyRestaurant> mdataset;

    public RestaurantListAdapter(List<ResponseNearByRestaurants.NearbyRestaurant> nearby_restaurants) {

            mdataset = nearby_restaurants;
    }

    @NonNull
    @Override
    public RestauratRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_restaurant,
                                        viewGroup, false);

        RestauratRowHolder holder = new RestauratRowHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestauratRowHolder restauratRowHolder, int position) {
        ResponseNearByRestaurants.NearbyRestaurant restaurant = mdataset.get(position);
        restauratRowHolder.bindData(restaurant);
    }

    @Override
    public int getItemCount() {
        return mdataset == null ? 0 : mdataset.size();
    }
}
