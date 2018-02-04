package com.example.customcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.customcheckbox.adapters.DataAdapters;
import com.example.customcheckbox.dataproviders.AllDataProvider;
import com.example.customcheckbox.interfaces.RetrofitInterface;
import com.example.customcheckbox.retrofit.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private List<AllDataProvider> list;
    private DataAdapters adapters;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ListView);
        retrofitInterface = RetrofitBuilder.getRetrofit().create(RetrofitInterface.class);
        Call<List<AllDataProvider>> call = retrofitInterface.getData();
        call.enqueue(new Callback<List<AllDataProvider>>() {
            @Override
            public void onResponse(Call<List<AllDataProvider>> call, Response<List<AllDataProvider>> response) {

                list = response.body();
                adapters = new DataAdapters(getApplicationContext(), list);
                listView.setAdapter(adapters);
            }

            @Override
            public void onFailure(Call<List<AllDataProvider>> call, Throwable t) {
                Log.e("Error on fetching Datas", call.toString() +"\t"+ t.toString());
            }
        });
    }
}
