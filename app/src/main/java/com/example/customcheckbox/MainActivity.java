package com.example.customcheckbox;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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
    private RecyclerView listView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(getApplicationContext());

        listView = (RecyclerView) findViewById(R.id.ListView);
        retrofitInterface = RetrofitBuilder.getRetrofit().create(RetrofitInterface.class);
        Call<List<AllDataProvider>> call = retrofitInterface.getData();
        call.enqueue(new Callback<List<AllDataProvider>>() {
            @Override
            public void onResponse(Call<List<AllDataProvider>> call, Response<List<AllDataProvider>> response) {

                list = response.body();
                adapters = new DataAdapters(getApplicationContext(), list);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(adapters);
            }

            @Override
            public void onFailure(Call<List<AllDataProvider>> call, Throwable t) {
                Log.e("Error on fetching Datas", call.toString() +"\t"+ t.toString());
            }
        });
    }

    public void setupActionBar(int listSize){

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        TextView mTextView = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.selectedNumber);
        mTextView.setText(String.valueOf(listSize));
    }
}
