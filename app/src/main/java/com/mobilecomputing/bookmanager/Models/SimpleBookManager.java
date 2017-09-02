package com.mobilecomputing.bookmanager.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Libra on 01/09/17.
 */

public class SimpleBookManager implements BookManager, Serializable {

    private ArrayList<Book> books;

    public SimpleBookManager(){

        books = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Book book = new Book();
            book.setTitle("Book"+i);
            book.setPrice(i);
            books.add(book);
        }

    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public Book getBook(int index) {
        return books.get(index);
    }

    @Override
    public Book createBook() {
        Book book = new Book();
        books.add(book);
        return book;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public void moveBook(int from, int to) {
        Book book = books.get(from);
        books.remove(from);
        books.add(to, book);
    }

    @Override
    public int getMinPrice() {
        Iterator<Book> itr = books.iterator();
        int min = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            min = min < next && min != -1 ? min : next;
        }
        return min;
    }

    @Override
    public int getMaxPrice() {
        Iterator<Book> itr = books.iterator();
        int max = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            max = max < next ? next : max;
        }
        return max;
    }

    @Override
    public float getMeanPrice() {
        Iterator<Book> itr = books.iterator();
        int total = -1;
        int bookCount = 0;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            total += next;
            bookCount++;
        }
        return bookCount > 0 ? total/bookCount : total;
    }

    @Override
    public int getTotalCost() {
        Iterator<Book> itr = books.iterator();
        int total = -1;
        while(itr.hasNext()) {
            int next = itr.next().getPrice();
            total += next;
        }
        return total;
    }

    @Override
    public void saveChanges() {

    }
}
