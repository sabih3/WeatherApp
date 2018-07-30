package com.ahmed.sabih.weatherapp.restaurants.data_source;

import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RestaurantsDataSource {

    @GET(EndPoints.GET_RESTAURANTS)
    Observable<ResponseNearByRestaurants> fetchNearByRestaurants(@Header("user-key")String header,@Query("lat") String lat,@Query("lon") String lon);
}
