package com.ahmed.sabih.weatherapp.core;

public class UserManager {

    private static UserManager sInstance;
    private static String userLat = "25.1985";
    private static String userLon = "55.2796";

    private UserManager() {
    }

    public static UserManager getInstance(){

        if(sInstance == null){
            sInstance = new UserManager();
            return sInstance;
        }else{
            return sInstance;
        }
    }

    public static String getUserLat(){
        return userLat;
    }

    public static String getUserLon(){
        return userLon;
    }

}
