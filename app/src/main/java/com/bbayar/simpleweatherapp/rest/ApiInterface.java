package com.bbayar.simpleweatherapp.rest;

import com.bbayar.simpleweatherapp.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    http://api.openweathermap.org/data/2.5/weather?units=metric&id=524901&appid=20506595c1c227a987bb75a5f0b26b1a

    @GET("weather/?units=metric")
    Call<City> getCityWeather(@Query("id") long id, @Query("appid") String appid);
}
