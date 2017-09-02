package com.mobilecomputing.bookmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilecomputing.bookmanager.Models.Book;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

public class SummaryActivity extends AppCompatActivity {

    private TextView bookCount;
    private TextView totalCost;
    private TextView mostExpensive;
    private TextView leastExpensive;
    private TextView avarageCost;
    BookManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        bookCount = (TextView) findViewById(R.id.summary_bookCount);
        totalCost = (TextView) findViewById(R.id.summary_totalCost);
        mostExpensive = (TextView) findViewById(R.id.summary_mostExpensive);
        leastExpensive = (TextView) findViewById(R.id.summary_leastExpensive);
        avarageCost = (TextView) findViewById(R.id.summary_avaragePrice);

        manager = new Gson().fromJson(getIntent().getStringExtra("Manager"), SimpleBookManager.class);

        String str1 = manager.count() == 0 ? "" : String.valueOf(manager.count()) + " ";
        bookCount.setText(str1);
        String str2 = manager.getTotalCost() < 0 ? "" : String.valueOf(manager.getTotalCost());
        totalCost.setText(str2);
        String str3 = manager.getMaxPrice() < 0 ? "" : String.valueOf(manager.getMaxPrice());
        mostExpensive.setText(str3);
        String str4 = manager.getMinPrice() < 0 ? "" : String.valueOf(manager.getMinPrice());
        leastExpensive.setText(str4);
        String str5 = manager.getMeanPrice() < 0 ? "" : String.valueOf(manager.getMeanPrice());
        avarageCost.setText(str5);
    }
}

