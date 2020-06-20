package com.vogella.android.recyclerview.presentation.view;

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
import com.vogella.android.recyclerview.Singletons;
import com.vogella.android.recyclerview.presentation.controller.MainController;
import com.vogella.android.recyclerview.presentation.model.Ghibli;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = Singletons.getMainController(MainActivity.this);
        controller.onStart();

    }



    public void showList(List<Ghibli> ghibliList) {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //input.add("I can add more");
        mAdapter = new ListAdapter(ghibliList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ghibli item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }



    public void navigateToAnotherActivity(Ghibli movie){
        Intent intent = new Intent(MainActivity.this, DetailsRecyclerViewElement.class);

        intent.putExtra("movieKey", Singletons.getGson().toJson(movie));
        MainActivity.this.startActivity(intent);
    }

}

