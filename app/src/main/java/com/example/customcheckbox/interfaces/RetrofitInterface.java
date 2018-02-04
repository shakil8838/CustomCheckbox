package com.example.customcheckbox.interfaces;

import com.example.customcheckbox.dataproviders.AllDataProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Anonymous on 1/30/2018.
 */
public interface RetrofitInterface {

    @GET("teachersData.php")
    Call<List<AllDataProvider>> getData();

}
