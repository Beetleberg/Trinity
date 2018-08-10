package com.example.george.tztrinity.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by George on 08.08.2018.
 */

public interface Api {
    @GET("users.json")
    Observable<List<User>> getData();
}
