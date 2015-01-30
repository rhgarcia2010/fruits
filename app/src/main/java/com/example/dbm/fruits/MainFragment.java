package com.example.dbm.fruits;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by reinier on 29/01/15.
 */
public class MainFragment extends Fragment {

    private OnMainInteractionListener mListener;
    private Button mListButton;
    private Button mRequestButton;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListButton = (Button)rootView.findViewById(R.id.button_list);

        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMainInteraction(v);
            }
        });

        mRequestButton = (Button)rootView.findViewById(R.id.button_request);

        mRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMainInteraction(v);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMainInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMainInteractionListenet");
        }
    }


    public interface OnMainInteractionListener{
        public void onMainInteraction(View v);
    }
}