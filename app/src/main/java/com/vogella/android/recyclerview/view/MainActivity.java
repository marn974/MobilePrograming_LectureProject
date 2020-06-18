package com.vogella.android.recyclerview.view;

import android.content.Context;
import android.content.Intent;
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
import com.vogella.android.recyclerview.Constants;
import com.vogella.android.recyclerview.controller.MainController;
import com.vogella.android.recyclerview.data.GhibliApi;
import com.vogella.android.recyclerview.model.Ghibli;

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

    private MainController controller;

    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this, new GsonBuilder().setLenient().create(), getSharedPreferences("application_esiea", Context.MODE_PRIVATE));
        controller.onStart();

    }



    public void showList(List<Ghibli> ghibliList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //input.add("I can add more");
        mAdapter = new ListAdapter(ghibliList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ghibli item) {
                navigateToAnotherActivity(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }



    public void navigateToAnotherActivity(Ghibli movie){
        Intent intent = new Intent(MainActivity.this, DetailsRecyclerViewElement.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("description", movie.getDescription());
        MainActivity.this.startActivity(intent);
    }

}

