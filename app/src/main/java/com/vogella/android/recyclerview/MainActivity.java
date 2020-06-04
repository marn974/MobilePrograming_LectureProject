package com.vogella.android.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td1_afteras_update.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private List<Ghibli> ghibliList;

    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();



        ghibliList = getDataFromCache();
        if(ghibliList != null){
            showList(ghibliList);
        }else{
            makeApiCall();
        }


    }

    private List<Ghibli> getDataFromCache() {
        //Get the string in "jsonPokemonList" that if empty is set to null
        String jsonGhibli = sharedPreferences.getString(Constants.KEY_GHIBLI_LIST, null);

        if(jsonGhibli == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Ghibli>>(){}.getType();
            return gson.fromJson(jsonGhibli, listType);
        }

    }


    private void showList(List<Ghibli> ghibliList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //input.add("I can add more");
        mAdapter = new ListAdapter(ghibliList);
        recyclerView.setAdapter(mAdapter);
    }


    static final String BASE_URL = "https://ghibliapi.herokuapp.com";
    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        final GhibliApi ghibliApi = retrofit.create(GhibliApi.class);

        Call<List<Ghibli>> call = ghibliApi.getGhibliResponse();

        call.enqueue(new Callback<List<Ghibli>>() {
            @Override
            public void onResponse(Call<List<Ghibli>> call, Response<List<Ghibli>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    ghibliList = response.body();
                    saveList(ghibliList);
                    showList(ghibliList);

                    Toast.makeText(getApplicationContext(),"API success", Toast.LENGTH_SHORT).show();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Ghibli>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void saveList(List<Ghibli> ghibliList) {
        String jsonString = gson.toJson(ghibliList);
        sharedPreferences
                .edit()
                .putString( Constants.KEY_GHIBLI_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List Sauvegardée", Toast.LENGTH_SHORT).show();
    }


    private void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }
}

