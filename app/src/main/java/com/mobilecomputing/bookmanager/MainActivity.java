package com.mobilecomputing.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, DetailActivity.class);
        //intent.putExtra("BookIndex", 0);


        BookManager manager = new SimpleBookManager();

        intent.putExtra("Manager", new Gson().toJson(manager).toString());

        intent.putExtra("Book", manager.getBook(0));
        startActivity(intent);

    }
}
