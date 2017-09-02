package com.mobilecomputing.bookmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilecomputing.bookmanager.Models.Book;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

public class SummaryActivity extends AppCompatActivity {

    private TextView totalCost;
    BookManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        totalCost = (TextView) findViewById(R.id.summary_totalCost);

        manager = new Gson().fromJson(getIntent().getStringExtra("Manager"), SimpleBookManager.class);

        String str = manager.getTotalCost() < 0 ? "" : String.valueOf(manager.getTotalCost());
        totalCost.setText(str);
    }
}

