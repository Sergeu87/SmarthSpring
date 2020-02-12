package com.example.smarthomeapp.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class RemoteUtils {

    private static String SERVER_IP_ADDRESS = "192.168.0.101";
    private static String SERVER_PORT = "9000";

    private static String API_BASE_URL = "http://" + SERVER_IP_ADDRESS + ":" + SERVER_PORT + "/";

    /**
     * Get retrofit obj retrofit.
     *
     * @return the retrofit
     */
    public static Retrofit getRetrofitObj(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(interceptor).build();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        OkHttpClient httpClient = httpClientBuilder.build();

        Retrofit retrofit = builder.client(httpClient).build();

        return retrofit;
    }
}
