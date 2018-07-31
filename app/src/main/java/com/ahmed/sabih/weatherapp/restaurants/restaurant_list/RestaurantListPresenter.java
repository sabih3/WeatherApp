package com.ahmed.sabih.weatherapp.restaurants.restaurant_list;

import com.ahmed.sabih.weatherapp.restaurants.data_source.RestaurantDataSourceImpl;
import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;

public class RestaurantListPresenter implements RestaurantListContract.Presenter,
                                                RestaurantListContract.RestaurantListInteractor
                                                                       .OnCompleteListener{

    private final RestaurantDataSourceImpl dataSourceImpl;
    private RestaurantListContract.View view;

    public RestaurantListPresenter(RestaurantListContract.View view,
                                   RestaurantDataSourceImpl dataSourceImpl) {
        this.view = view;
        this.dataSourceImpl = dataSourceImpl;

    }

    @Override
    public void fetchData() {

        view.showProgress();
        dataSourceImpl.getNearByRestaurants(this);



    }

    @Override
    public void onDestroy() {
        this.view = null;
    }


    @Override
    public void onCompeted(ResponseNearByRestaurants responseNearByRestaurants) {
        //This can take time, and user can get away the screen
        if(view != null){
            view.hideProgress();
            view.setDataInList(responseNearByRestaurants);
        }

    }

    @Override
    public void onFailure(Throwable e) {
        if(view != null){
            view.hideProgress();
            view.onError();
        }

    }
}
