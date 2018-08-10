package com.example.george.tztrinity.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by George on 10.08.2018.
 */

public class ApiClient {
    private  static ApiClient instance;
    private  Retrofit retrofit;

    private ApiClient (){
        buildRetrofit();
    }

    public synchronized static ApiClient getInstance(){
        if (instance == null)
            instance = new ApiClient();
        return instance;
    }

    private  Retrofit buildRetrofit(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://a11d.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpBuilder.build())
                .build();

        return  retrofit;
    }

    public Api getApi(){
        return  retrofit.create(Api.class);
    }
}