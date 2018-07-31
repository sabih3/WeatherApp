package com.ahmed.sabih.weatherapp.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.sabih.weatherapp.R;

import java.util.ArrayList;

import github.vatsal.easyweather.retrofit.models.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastHolder>{

    private ArrayList<List> mDataset;

    public ForecastAdapter(ArrayList<List> forecastDataList) {
        this.mDataset = forecastDataList;
    }

    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View forecastRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_layout,
                            parent, false);

        ForecastHolder holder = new ForecastHolder(forecastRow);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        List forecast = mDataset.get(position);
        holder.bindData(forecast);
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
