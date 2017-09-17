package com.group5.bookmanager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.group5.bookmanager2.Models.Book;
import com.group5.bookmanager2.Models.BookManager;
import com.group5.bookmanager2.R;

public class AddBookActivity extends AppCompatActivity {

    private EditText title_in;
    private EditText author_in;
    private EditText course_in;
    private EditText ISBN_in;
    private EditText price_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title_in = (EditText) findViewById(R.id.addBook_title);
        author_in = (EditText) findViewById(R.id.addBook_author);
        course_in = (EditText) findViewById(R.id.addBook_course);
        ISBN_in = (EditText) findViewById(R.id.addBook_ISBN);
        price_in = (EditText) findViewById(R.id.addBook_price);

    }

    private boolean addBook(){

        String title = title_in.getText().toString().trim();
        String author = author_in.getText().toString().trim();
        String course = course_in.getText().toString().trim();
        String ISBN = ISBN_in.getText().toString().trim();
        String price_String = price_in.getText().toString().trim();

        int price = price_String.isEmpty() ? 0 : Integer.parseInt(price_String);


        if(title == null || title.isEmpty()){
            title_in.setError("The book must have a title!");
            title_in.requestFocus();
            return false;
        }


        Book newBook = BookManager.getBookmanager(getSharedPreferences(BookManager.PREFS_NAME, 0)).createBook();
        newBook.setTitle(title);
        if(author != null) newBook.setAuthor(author);
        if(course != null) newBook.setCourse(course);
        if(ISBN != null) newBook.setIsbn(ISBN);
        newBook.setPrice(price);


        return true;

    }


    public void done(View view){
        Log.d("AddBook", "done" );

        if(addBook()){
            BookManager.getBookmanager(getSharedPreferences(BookManager.PREFS_NAME, 0)).createBook();
            this.finish();
        }

    }

    public void cancel(View view){
        Log.d("AddBook", "cancel" );

        finish();
    }

}
