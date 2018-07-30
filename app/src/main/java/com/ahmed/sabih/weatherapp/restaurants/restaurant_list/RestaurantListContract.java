package com.ahmed.sabih.weatherapp.restaurants.restaurant_list;

import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;

public interface RestaurantListContract {

    interface View{
        void showProgress();
        void hideProgress();
        void setDataInList(ResponseNearByRestaurants responseNearByRestaurants);
        void onError();
    }

    interface Presenter{
        void onDestroy();
        void fetchData();

    }

    interface RestaurantListInteractor{

        interface OnCompleteListener{
            void onCompeted(ResponseNearByRestaurants responseNearByRestaurants);
            void onFailure(Throwable e);
        }

        void getNearByRestaurants(OnCompleteListener onCompleteListener);

    }

}
