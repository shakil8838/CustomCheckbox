package com.example.customcheckbox.retrofit;

import com.example.customcheckbox.APIs.URLs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anonymous on 1/30/2018.
 */
public class RetrofitBuilder {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.FETCHING_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
