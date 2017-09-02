package com.mobilecomputing.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import com.mobilecomputing.bookmanager.Models.Book;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
