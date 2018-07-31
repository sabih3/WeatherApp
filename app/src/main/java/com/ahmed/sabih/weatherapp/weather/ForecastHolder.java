package com.ahmed.sabih.weatherapp.weather;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.sabih.weatherapp.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.vatsal.easyweather.retrofit.models.List;

class ForecastHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_day)TextView tvForecastDay;
    @BindView(R.id.forecast_temp)TextView tvForecastTemp;
    @BindView(R.id.iv_forecast_icon)ImageView forecaseIcon;

     public ForecastHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);


    }

    public void bindData(List forecast) {

        tvForecastDay.setText("MON");
        tvForecastTemp.setText(forecast.getMain().getTemp_max()+" "+ forecast.getMain().getTemp_min());


        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(forecast.getDt()) * 1000L);
        String week = DateFormat.format("E", cal).toString();
        tvForecastDay.setText(week);

        Glide.with(itemView.getContext()).load(forecast.getWeather()[0].getIconLink()).into(forecaseIcon);
    }
}
