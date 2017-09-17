package com.group5.bookmanager2.Models;

import android.content.SharedPreferences;
import android.service.autofill.Dataset;
import android.util.Log;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Libra on 01/09/17.
 */

public class BookManager implements Serializable {

    public static final String PREFS_NAME = "MyPrefsFile";


    private ArrayList<Book> books;
    private static BookManager manager;
    private SharedPreferences prefs;

    private BookManager(SharedPreferences prefs){


        books = new ArrayList<>();
        this.prefs = prefs;

        /*for(int i = 0; i < 5; i++){
            Book book = new Book();
            book.setTitle("Book"+i);
            book.setPrice(i);
            books.add(book);
        }*/

    }

    public int count() {
        return books.size();
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public Book createBook() {
        Book book = new Book();
        books.add(book);

        saveChanges();

        return book;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);

        saveChanges();
    }

    public void moveBook(int from, int to) {
        Book book = books.get(from);
        books.remove(from);
        books.add(to, book);
    }

    public int getMinPrice() {
        Iterator<Book> itr = books.iterator();
        int min = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            min = min < next && min != -1 ? min : next;
        }
        return min;
    }

    public int getMaxPrice() {
        Iterator<Book> itr = books.iterator();
        int max = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            max = max < next ? next : max;
        }
        return max;
    }

    public float getMeanPrice() {
        Iterator<Book> itr = books.iterator();
        int total = -1;
        int bookCount = 0;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            total += (total == -1 ? next+1 : next == -1 ? 0 : next);
            bookCount++;
        }
        return bookCount > 0 ? total/bookCount : total;
    }

    public int getTotalCost() {
        Iterator<Book> itr = books.iterator();
        int total = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            total += (total == -1 ? next+1 : next == -1 ? 0 : next);
        }
        return total;
    }

    public void saveChanges() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Set<String> savedBooks = new HashSet<>();

        for (Book b : books) {
            String jsonBook = gson.toJson(b);
            savedBooks.add(jsonBook);

            Log.d("BookManager", jsonBook);
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("SAVED_BOOKS", savedBooks).apply();

    }

    public static BookManager getBookmanager(SharedPreferences prefs) {
        if(manager == null) manager = new BookManager(prefs);
        return manager;
    }
}
