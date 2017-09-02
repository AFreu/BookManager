package com.mobilecomputing.bookmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobilecomputing.bookmanager.Models.Book;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

import org.json.JSONObject;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, SummaryActivity.class);
        //intent.putExtra("BookIndex", 0);


        BookManager manager = new SimpleBookManager();

        intent.putExtra("Manager", new Gson().toJson(manager).toString());

        intent.putExtra("Book", manager.getBook(0));
        startActivity(intent);

    }
}
