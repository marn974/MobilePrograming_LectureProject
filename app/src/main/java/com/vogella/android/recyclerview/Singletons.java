package com.vogella.android.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.recyclerview.data.GhibliApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gson;
    private static GhibliApi ghibliApi;
    private static SharedPreferences sharedPreferences;

    public static Gson getGson() {

        if(gson == null)
            gson = new GsonBuilder().setLenient().create();

        return gson;

    }

    public static GhibliApi getGhibliApi() {
        if(ghibliApi== null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            ghibliApi = retrofit.create(GhibliApi.class);
        }

        return ghibliApi;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if(sharedPreferences== null){
            sharedPreferences = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }
}
