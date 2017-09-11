package com.group5.bookmanager2.Models;

import android.support.v4.app.Fragment;
import android.content.Context;

/**
 * Created by Libra on 11/09/17.
 */

public abstract class BaseFragment extends Fragment {

    protected FragmentListener mListener;
    protected BookManager mBookManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            mListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
