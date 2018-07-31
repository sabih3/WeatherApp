package com.ahmed.sabih.weatherapp.core;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmed.sabih.weatherapp.R;
import com.ahmed.sabih.weatherapp.core.adapters.FragmentAdapter;
import com.ahmed.sabih.weatherapp.model.FragmentHolder;
import com.ahmed.sabih.weatherapp.restaurants.restaurant_list.ScreenRestaurantList;
import com.ahmed.sabih.weatherapp.weather.weather_info.ScreenWeatherInfo;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_ID = 232;
    private static final int REQUEST_CHECK_SETTINGS = 123;
    private FusedLocationProviderClient mFusedLocationClient;

    @BindView(R.id.main_pager)ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        ViewPagerHelper pagerHelper = new ViewPagerHelper();
        FragmentAdapter viewPagerAdapter = pagerHelper.
                                           getViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(viewPagerAdapter);

        checkLocationPermission();




    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            // Permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_ID);

            return;
        }else{
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //TODO: Fix location null issue
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_ID:

                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();


                    SettingsClient client = LocationServices.getSettingsClient(this);
                    Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

                    task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                        }
                    });

                    task.addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof ResolvableApiException) {
                                // Location settings are not satisfied, but this can be fixed
                                // by showing the user a dialog.
                                try {
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    ResolvableApiException resolvable = (ResolvableApiException) e;
                                    resolvable.startResolutionForResult(MainActivity.this,
                                            REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sendEx) {
                                    // Ignore the error.
                                }
                            }

                        }
                    });


                    mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                        }
                    });
                }else{
                    checkLocationPermission();
                }

        }

    }
}
