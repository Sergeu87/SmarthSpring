package com.example.smarthomeapp.util;

import com.example.smarthomeapp.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAuthService {

    @GET("login")
    Call<User> login(@Query("username") String login, @Query("password") String password);

}
