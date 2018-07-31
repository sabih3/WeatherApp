package com.ahmed.sabih.weatherapp.restaurants.restaurant_list;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.core.UIUtils;
import com.ahmed.sabih.weatherapp.restaurants.adapter.RestaurantListAdapter;
import com.ahmed.sabih.weatherapp.restaurants.data_source.RestaurantDataSourceImpl;
import com.ahmed.sabih.weatherapp.restaurants.model.ResponseNearByRestaurants;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenRestaurantList extends Fragment implements RestaurantListContract.View{

    RestaurantListPresenter presenter;


    public ScreenRestaurantList() {
    }

    @BindView(R.id.screen_restaurant_coordinator)CoordinatorLayout parentCoordinator;
    @BindView(R.id.rv_restaurant) RecyclerView restaurantListView;
    @BindView(R.id.ptr_restaurant)SwipeRefreshLayout pullToRefresh;
    @BindString(R.string.exception_general_purpose) String EXCEPTION_GENERAL;
    @BindString(R.string.btn_retry)String btnRetry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_restaurant_list, container, false);

        ButterKnife.bind(this,rootView);

        presenter = new RestaurantListPresenter(this,new RestaurantDataSourceImpl());
        presenter.fetchData();

        pullToRefresh.setOnRefreshListener(new PullToRefreshListener());
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    //RestaurantListContract.View
    @Override
    public void showProgress() {
        pullToRefresh.setRefreshing(true);
    }

    ////RestaurantListContract.View
    @Override
    public void hideProgress() {
        pullToRefresh.setRefreshing(false);
    }

    ////RestaurantListContract.View
    @Override
    public void setDataInList(ResponseNearByRestaurants responseNearByRestaurants) {
        restaurantListView.setLayoutManager(new LinearLayoutManager(getContext()));
        RestaurantListAdapter adapter = new RestaurantListAdapter(
                                        responseNearByRestaurants.nearby_restaurants);
        restaurantListView.setAdapter(adapter);


    }
    //RestaurantListContract.View
    @Override
    public void onError() {
        UIUtils.showSnackBar(parentCoordinator, EXCEPTION_GENERAL, btnRetry,
                new UIUtils.SnackBarActionListener() {
            @Override
            public void onSnackBarAction() {
                presenter.fetchData();
            }
        });
    }

    private class PullToRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            presenter.fetchData();
        }
    }


}
