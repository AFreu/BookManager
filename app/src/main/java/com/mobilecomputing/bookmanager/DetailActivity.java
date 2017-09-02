package com.mobilecomputing.bookmanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilecomputing.bookmanager.Models.Book;
import com.mobilecomputing.bookmanager.Models.BookManager;
import com.mobilecomputing.bookmanager.Models.SimpleBookManager;

public class DetailActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.details_title);


        Book book = (Book) getIntent().getSerializableExtra("Book");

        BookManager manager = new Gson().fromJson(getIntent().getStringExtra("Manager"), SimpleBookManager.class);

        //book = manager.getBook(0);

        title.setText(book.getTitle());
    }
}
