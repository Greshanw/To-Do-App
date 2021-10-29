package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Database.DBHelper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    MyAdapter myAdapter;
    Button addnew;
    ArrayList<Items> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addnew = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        dbHelper = new DBHelper(this);
        list = new ArrayList<>();
        list = dbHelper.readAllTasks();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);


        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Add_task.class);
                startActivity(intent);
            }
        });
    }
}