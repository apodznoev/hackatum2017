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
import com.example.android.saufdeckel.ui.activities.MainActivity;
import com.example.android.saufdeckel.ui.adapters.overview.AllCoastersAdapter;
import com.example.android.saufdeckel.ui.fragments.detail.CoasterDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 18.11.17.
 */

public class AllCoastersFragment extends Fragment implements AllCoastersAdapter.OnItemClickListener{


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
        mAdapter = new AllCoastersAdapter(getActivity(), getAllCoasters(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private List<Coaster> getAllCoasters() {

        List<Coaster> dummyCoasters = new ArrayList<Coaster>();
        dummyCoasters.add(new Coaster("Charlie Sheen", 3, "http://i.dailymail.co.uk/i/pix/2012/03/12/article-0-121D6E7E000005DC-528_634x612.jpg"));
        dummyCoasters.add(new Coaster("Ozzy Osbourne", 5, "http://www.audio-music.info/pic/Osbourne_Ozzy.jpg"));
        dummyCoasters.add(new Coaster("Lindsey Lohan", 1, "http://popbytes.com/img/lindsay-lohan-jan5-JF.jpg"));
        dummyCoasters.add(new Coaster("Britney Spears", 4, "http://www.evilbeetgossip.com/wp-content/uploads/2012/09/Britney-Spears-5.jpg"));


        dummyCoasters.get(0).addDrink(new Drink("Gin Tonic", 0.99, Drink.DrinkType.COCKTAIL));

        return dummyCoasters;
    }

    @Override
    public void onCoasterItemClick(Coaster clickedCoaster) {
        ((MainActivity)getActivity()).changeFragment(CoasterDetailFragment.newInstance(clickedCoaster));
    }
}
