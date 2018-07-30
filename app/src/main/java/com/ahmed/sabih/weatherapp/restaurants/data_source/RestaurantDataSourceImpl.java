package com.ahmed.sabih.weatherapp.restaurants.data_source;

import com.ahmed.sabih.weatherapp.restaurants.restaurant_list.RestaurantListContract;
import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantDataSourceImpl implements RestaurantListContract.RestaurantListInteractor{

    private RestaurantsDataSource api;


    public RestaurantDataSourceImpl() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);



        Retrofit retrofit = new Retrofit.Builder()
                            .client(httpClient.build())
                            .baseUrl(EndPoints.RESTAURANTS_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                            .build();


        api = retrofit.create(RestaurantsDataSource.class);

    }

    @Override
    public void getNearByRestaurants(final OnCompleteListener onCompleteListener) {
        api.fetchNearByRestaurants("f97c65f47221aaad092e22075368d1d6","25.2048","55.2708")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseNearByRestaurants>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseNearByRestaurants responseNearByRestaurants) {
                        onCompleteListener.onCompeted(responseNearByRestaurants);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onCompleteListener.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
