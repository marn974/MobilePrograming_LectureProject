package com.vogella.android.recyclerview.presentation.controller;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vogella.android.recyclerview.Constants;
import com.vogella.android.recyclerview.Singletons;
import com.vogella.android.recyclerview.presentation.model.Ghibli;
import com.vogella.android.recyclerview.presentation.view.MainActivity;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//liste les actions utilisateurs lié à la main activity
public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private List<Ghibli> ghibliList;
    private MainActivity view;

    public MainController(MainActivity view, Gson gson, SharedPreferences sharedPreferences) {
        this.view = view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){

        ghibliList = getDataFromCache();
        if(ghibliList != null){
            view.showList(ghibliList);
        }else{
            makeApiCall();
        }

    }

    public void onItemClick(Ghibli movie){
        view.navigateToAnotherActivity(movie);
    }

    private void makeApiCall(){
        
        Call<List<Ghibli>> call = Singletons.getGhibliApi().getGhibliResponse();

        call.enqueue(new Callback<List<Ghibli>>() {
            @Override
            public void onResponse(Call<List<Ghibli>> call, Response<List<Ghibli>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    ghibliList = response.body();
                    saveList(ghibliList);
                    view.showList(ghibliList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Ghibli>> call, Throwable t) {
                view.showError();
            }

        });

    }

    private void saveList(List<Ghibli> ghibliList) {
        String jsonString = gson.toJson(ghibliList);
        sharedPreferences
                .edit()
                .putString( Constants.KEY_GHIBLI_LIST, jsonString)
                .apply();

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


}
