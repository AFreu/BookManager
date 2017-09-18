package com.group5.bookmanager2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;

import com.group5.bookmanager2.Models.Book;
import com.group5.bookmanager2.Models.BookManager;

import static com.group5.bookmanager2.CollectionFragment.BOOK_POS_TAG;

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
    private BookManager bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = (AppCompatTextView) findViewById(R.id.detail_title);
        authorTextView = (AppCompatTextView) findViewById(R.id.detail_author);
        isbnTextView = (AppCompatTextView) findViewById(R.id.detail_isbn);
        priceTextView = (AppCompatTextView) findViewById(R.id.detail_price);
        courseTextView = (AppCompatTextView) findViewById(R.id.detail_course);

        bm = BookManager.getBookmanager(getSharedPreferences(BookManager.PREFS_NAME, 0));
        book = bm.getBook(getIntent().getIntExtra(BOOK_POS_TAG, 0));

        updateUI();
    }

    public void updateUI() {

        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        isbnTextView.setText(book.getIsbn());
        priceTextView.setText("" + book.getPrice());
        courseTextView.setText(book.getCourse());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit_book) {
            Intent intent = new Intent(this, AddBookActivity.class);
            intent.putExtra(BOOK_POS_TAG, book);
            startActivity(intent);
            return true;
        } else if (id == R.id.remove_book) {

            BookManager bm = BookManager.getBookmanager(getSharedPreferences(BookManager.PREFS_NAME, 0));
            bm.removeBook(book);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
