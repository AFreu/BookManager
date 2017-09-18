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
import java.util.List;
import java.util.Set;

/**
 * Created by Libra on 01/09/17.
 */

public class BookManager implements Serializable {

    public interface BookManagerListener {
        void bookDataChanged();
    }

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREFS_BOOK_TAG = "SAVED_BOOKS";

    private ArrayList<Book> books;
    private static BookManager manager;
    private SharedPreferences prefs;

    private List<BookManagerListener> listeners;

    private BookManager(SharedPreferences prefs){

        listeners = new ArrayList<>();
        books = new ArrayList<>();
        this.prefs = prefs;

        loadBooks();
    }

    public void addListener(BookManagerListener listener) {
        listeners.add(listener);
    }

    public int count() {
        return books.size();
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public String getBookJson(int index) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(books.get(index));
    }

    public Book createBook() {
        Book book = new Book();
        books.add(book);

        for(BookManagerListener listener : listeners)
            listener.bookDataChanged();

        saveChanges();

        return book;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);

        for(BookManagerListener listener : listeners)
            listener.bookDataChanged();

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
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(PREFS_BOOK_TAG, savedBooks).apply();

    }

    public static BookManager getBookmanager(SharedPreferences prefs) {
        if(manager == null) manager = new BookManager(prefs);
        return manager;
    }

    public void loadBooks() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Set<String> jsonBooks = prefs.getStringSet(PREFS_BOOK_TAG, new HashSet<String>());

        for(String jsonString : jsonBooks)
            books.add(gson.fromJson(jsonString, Book.class));
    }
}
