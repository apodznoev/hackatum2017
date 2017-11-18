package com.example.android.saufdeckel.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.android.saufdeckel.ui.activities.MainActivity;


public abstract class BaseParentFragment extends Fragment {
    public MainActivity parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity)getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setNavTitle();
    }

    public void setNavTitle() {
        if ( parentActivity != null )
            parentActivity.setTitle(getNavTitle());
    }
    public abstract String getNavTitle();
}
