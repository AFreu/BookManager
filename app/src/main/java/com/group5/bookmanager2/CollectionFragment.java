package com.group5.bookmanager2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group5.bookmanager2.Models.BaseFragment;
import com.group5.bookmanager2.Models.Book;
import com.group5.bookmanager2.Models.BookManager;

/*
*/

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //SummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends BaseFragment implements BookManager.BookManagerListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "MANAGER";

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
        bookAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                getContext().startActivity(intent);
            }
        });

        bm = BookManager.getBookmanager(getContext().getSharedPreferences(BookManager.PREFS_NAME, 0));
        bm.addListener(this);

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
