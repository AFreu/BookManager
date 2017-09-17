package com.group5.bookmanager2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group5.bookmanager2.Models.Book;

import java.util.HashSet;
import java.util.Set;

import static com.group5.bookmanager2.CollectionFragment.BOOK_TAG;

/**
 * Created by mikael on 2017-09-17.
 */

public class DetailActivity extends AppCompatActivity {

    private AppCompatTextView titleTextView;
    private AppCompatTextView authorTextView;
    private AppCompatTextView isbnTextView;
    private AppCompatTextView priceTextView;
    private AppCompatTextView courseTextView;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String bookJson = getIntent().getStringExtra(BOOK_TAG);

        titleTextView = (AppCompatTextView) findViewById(R.id.detail_title);
        authorTextView = (AppCompatTextView) findViewById(R.id.detail_author);
        isbnTextView = (AppCompatTextView) findViewById(R.id.detail_isbn);
        priceTextView = (AppCompatTextView) findViewById(R.id.detail_price);
        courseTextView = (AppCompatTextView) findViewById(R.id.detail_course);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        book = gson.fromJson(bookJson, Book.class);

        updateUI();
    }

    public void updateUI() {

        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        isbnTextView.setText("" + book.getIsbn());
        priceTextView.setText("" + book.getPrice());
        courseTextView.setText(book.getCourse());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
