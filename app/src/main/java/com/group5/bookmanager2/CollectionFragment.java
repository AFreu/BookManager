package com.group5.bookmanager2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group5.bookmanager2.Models.BaseFragment;
import com.group5.bookmanager2.Models.Book;
import com.group5.bookmanager2.Models.BookManager;

public class CollectionFragment extends BaseFragment
        implements BookManager.BookManagerListener {

    public static final String BOOK_TAG = "BOOK";

    // TODO: Rename and change types of parameters
    private ListView bookList;
    private ArrayAdapter<String> bookAdapter;
    private BookManager bm;

    public CollectionFragment() {
        // Required empty public constructor
    }

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        bookList = view.findViewById(R.id.book_list);
        bookAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        bm = BookManager.getBookmanager(getContext().getSharedPreferences(BookManager.PREFS_NAME, 0));
        bm.addListener(this);

        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(BOOK_TAG, bm.getBookJson(position));
                getContext().startActivity(intent);
            }
        });

        updateUI();

        bookList.setAdapter(bookAdapter);
        return view;
    }

    public void updateUI() {

        bookAdapter.clear();

        for(Book book : bm.getAllBooks())
            bookAdapter.add(book.getTitle() + " by " + book.getAuthor());

        bookAdapter.notifyDataSetChanged();
    }


    @Override
    public void bookDataChanged() {
        updateUI();
    }
}
