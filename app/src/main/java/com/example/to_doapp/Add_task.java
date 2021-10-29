package com.example.to_doapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHelper;

public class Add_task extends AppCompatActivity {

    EditText newTask;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        newTask = findViewById(R.id.newTask);
        save = findViewById(R.id.btnSave);
    }

    public void save(View view){
        String task = newTask.getText().toString();

        if(task.isEmpty()){
            Toast.makeText(Add_task.this, "Please enter task", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper dbHelper = new DBHelper(Add_task.this);

            AlertDialog.Builder builder = new AlertDialog.Builder(Add_task.this);
            builder.setTitle("Conform add task");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper dbHelper = new DBHelper(Add_task.this);

                    long inserted = dbHelper.addTask(task);

                    if(inserted > 0){
                        Toast.makeText(Add_task.this, "Task added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add_task.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Add_task.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}