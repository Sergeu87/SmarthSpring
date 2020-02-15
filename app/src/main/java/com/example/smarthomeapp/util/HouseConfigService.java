package com.example.smarthomeapp.util;

import com.example.smarthomeapp.model.HomeConfigEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HouseConfigService {

    @GET("config")
    Call<HomeConfigEntity> getHomeConfig();

}
