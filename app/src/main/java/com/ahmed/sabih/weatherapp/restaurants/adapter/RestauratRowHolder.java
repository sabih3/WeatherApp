package com.ahmed.sabih.weatherapp.restaurants.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestauratRowHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_res_name)TextView restaurantName;
    @BindView(R.id.tv_cuisine)TextView restaurantCuisine;
    @BindView(R.id.tv_city)TextView restaurantCity;
    @BindView(R.id.tv_rating)TextView restaurantRating;
    @BindView(R.id.tv_reviews)TextView restaurantReviews;

    @BindView(R.id.iv_thumbnail)ImageView restaurantThumbnail;


    public RestauratRowHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }

    public void bindData(ResponseNearByRestaurants.NearbyRestaurant restaurant) {

        restaurantName.setText(restaurant.getRestaurant().getName());
        restaurantCuisine.setText(restaurant.getRestaurant().getCuisines());
        restaurantCity.setText(restaurant.getRestaurant().getLocation().getCity());
        restaurantRating.setText(restaurant.getRestaurant().getUser_rating().getAggregate_rating());
        restaurantReviews.setText(restaurant.getRestaurant().getUser_rating().getVotes());
        Glide.with(itemView.getContext()).load(restaurant.restaurant.thumb).into(restaurantThumbnail);
    }
}
