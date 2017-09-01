package com.mobilecomputing.bookmanager.Models;

import java.util.ArrayList;

/**
 * Created by Libra on 01/09/17.
 */

public class SimpleBookManager implements BookManager {

    private ArrayList<Book> books;

    public SimpleBookManager(){

        books = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            books.add(new Book());
        }

    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public Book getBook(int index) {
        return null;
    }

    @Override
    public Book createBook() {
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void removeBook(Book book) {

    }

    @Override
    public void moveBook(int from, int to) {

    }

    @Override
    public int getMinPrice() {
        return 0;
    }

    @Override
    public int getMaxPrice() {
        return 0;
    }

    @Override
    public float getMeanPrice() {
        return 0;
    }

    @Override
    public int getTotalCost() {
        return 0;
    }

    @Override
    public void saveChanges() {

    }
}
