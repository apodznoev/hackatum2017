package com.example.android.saufdeckel.ui.fragments.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;
import com.example.android.saufdeckel.ui.adapters.detail.CoasterDrinksAdapter;
import com.example.android.saufdeckel.ui.fragments.BaseParentFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lars on 18.11.17.
 */

public class CoasterDetailFragment extends BaseParentFragment {

    private static final String ARG_COASTER = "arg_coaster";
    private Unbinder mUnbinder;
    private Coaster mCoaster;
    private RecyclerView mRecyclerView;
    private CoasterDrinksAdapter mAdapter;

    public CoasterDetailFragment() {
        // Required empty public constructor
    }

    public static CoasterDetailFragment newInstance(Coaster clickedCoaster) {
        CoasterDetailFragment fragment = new CoasterDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COASTER, clickedCoaster);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCoaster = getArguments().getParcelable(ARG_COASTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coaster_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new CoasterDrinksAdapter(getActivity(), mCoaster.getAllDrinks());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public String getNavTitle() {
        return "Coaster: " + mCoaster.getName();
    }



    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
