package com.group5.bookmanager2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group5.bookmanager2.Models.BookManager;

/*
*//**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //SummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "MANAGER";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView bookCount;
    private TextView totalCost;
    private TextView mostExpensive;
    private TextView leastExpensive;
    private TextView avarageCost;
    BookManager manager;
    //private OnFragmentInteractionListener mListener;

    public SummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        bookCount = (TextView) view.findViewById(R.id.summary_bookCount);
        totalCost = (TextView) view.findViewById(R.id.summary_totalCost);
        mostExpensive = (TextView) view.findViewById(R.id.summary_mostExpensive);
        leastExpensive = (TextView) view.findViewById(R.id.summary_leastExpensive);
        avarageCost = (TextView) view.findViewById(R.id.summary_avaragePrice);

        manager = BookManager.getBookmanager();

        String str1 = manager.count() == 0 ? "" : String.valueOf(manager.count());
        bookCount.setText(str1);
        String str2 = manager.getTotalCost() < 0 ? "" : String.valueOf(manager.getTotalCost());
        totalCost.setText(str2);
        String str3 = manager.getMaxPrice() < 0 ? "" : String.valueOf(manager.getMaxPrice());
        mostExpensive.setText(str3);
        String str4 = manager.getMinPrice() < 0 ? "" : String.valueOf(manager.getMinPrice());
        leastExpensive.setText(str4);
        String str5 = manager.getMeanPrice() < 0 ? "" : String.valueOf(manager.getMeanPrice());
        avarageCost.setText(str5);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
