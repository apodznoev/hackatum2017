package com.example.android.saufdeckel.ui.fragments.overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.saufdeckel.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lars on 18.11.17.
 */

public class EmptyDrinkFragment extends Fragment {


    private Unbinder mUnbinder;

    public EmptyDrinkFragment() {
        // Required empty public constructor
    }

    public static EmptyDrinkFragment newInstance() {
        EmptyDrinkFragment fragment = new EmptyDrinkFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_list_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);


        return view;
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
