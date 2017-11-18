package com.example.android.saufdeckel.ui.fragments.overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;
import com.example.android.saufdeckel.ui.adapters.AllCoastersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lars on 18.11.17.
 */

public class AllCoastersFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private AllCoastersAdapter mAdapter;


    public AllCoastersFragment() {
        // Required empty public constructor
    }

    public static AllCoastersFragment newInstance() {
        AllCoastersFragment fragment = new AllCoastersFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_list_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new AllCoastersAdapter(getActivity(), getAllCoasters());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private List<Coaster> getAllCoasters() {

        List<Coaster> dummyCoasters = new ArrayList<Coaster>();
        dummyCoasters.add(new Coaster("Charly Sheen", 3));
        dummyCoasters.add(new Coaster("Ozzy Osbourne", 5));
        dummyCoasters.add(new Coaster("Lindsey Lohan", 1));
        dummyCoasters.add(new Coaster("Britney Spears", 4));


        dummyCoasters.get(0).addDrink(new Drink("Gin Tonic", Drink.DrinkType.Cocktail));

        return dummyCoasters;
    }

}
