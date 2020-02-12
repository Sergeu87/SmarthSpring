package com.example.smarthomeapp.util;

import com.example.utils.domain.HomeConfigEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HouseConfigService {

    @GET("config")
    Call<HomeConfigEntity> getHomeConfig();

}
