package com.group5.bookmanager2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.group5.bookmanager2.Models.Book;
import com.group5.bookmanager2.Models.BookManager;

import static com.group5.bookmanager2.CollectionFragment.BOOK_POS_TAG;
import static com.group5.bookmanager2.DetailActivity.ERROR_CODE_NO_BOOK;

public class AddBookActivity extends AppCompatActivity {

    private EditText title_in;
    private EditText author_in;
    private EditText course_in;
    private EditText ISBN_in;
    private EditText price_in;

    private Book currentBook;
    private BookManager bm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title_in = (EditText) findViewById(R.id.addBook_title);
        author_in = (EditText) findViewById(R.id.addBook_author);
        course_in = (EditText) findViewById(R.id.addBook_course);
        ISBN_in = (EditText) findViewById(R.id.addBook_ISBN);
        price_in = (EditText) findViewById(R.id.addBook_price);

        bm = BookManager.getBookmanager(getSharedPreferences(BookManager.PREFS_NAME, 0));

        updateUI();
    }

    public void updateUI() {

        int bookPosition = getIntent().getIntExtra(BOOK_POS_TAG, ERROR_CODE_NO_BOOK);

        Log.d("BookManager", "pos: " + bookPosition);
        // if no book was sent
        if(bookPosition == ERROR_CODE_NO_BOOK)
            return;

        currentBook = bm.getBook(bookPosition);

        if(currentBook.getTitle() != null)
            title_in.setText(currentBook.getTitle());

        if(currentBook.getAuthor() != null)
            author_in.setText(currentBook.getAuthor());

        if(currentBook.getIsbn() != null)
            ISBN_in.setText(currentBook.getIsbn());

        price_in.setText("" + currentBook.getPrice());

        if(currentBook.getCourse() != null)
            course_in.setText(currentBook.getCourse());

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


        Book newBook = bm.createBook();
        newBook.setTitle(title);
        if(author != null) newBook.setAuthor(author);
        if(course != null) newBook.setCourse(course);
        if(ISBN != null) newBook.setIsbn(ISBN);
        newBook.setPrice(price);

        bm.updateListeners();

        return true;
    }


    public void done(View view){
        Log.d("AddBook", "done" );

        if(addBook()){
            this.finish();
        }

    }

    public void cancel(View view){
        Log.d("AddBook", "cancel" );

        finish();
    }

}
