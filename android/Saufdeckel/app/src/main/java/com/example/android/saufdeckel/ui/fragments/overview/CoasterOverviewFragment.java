package com.example.android.saufdeckel.ui.fragments.overview;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;


import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.ui.adapters.ViewPagerAdapter;
import com.example.android.saufdeckel.ui.fragments.BaseParentFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CoasterOverviewFragment extends BaseParentFragment {

    private Unbinder mUnbinder;

    public CoasterOverviewFragment() {
        // Required empty public constructor
    }

    public static CoasterOverviewFragment newInstance() {
        CoasterOverviewFragment fragment = new CoasterOverviewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coaster_overview, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setupViewPager(view);
        return view;
    }

    protected void setupViewPager(View view) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_coaster_overview);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs_coaster_overview);
        tabLayout.setupWithViewPager(viewPager);
    }

    protected void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(EmptyDrinkFragment.newInstance(), getResources().getString(R.string.empty_drinks));
        adapter.addFrag(AllCoastersFragment.newInstance(), getResources().getString(R.string.all_coasters));

        viewPager.setAdapter(adapter);
    }

    @Override
    public String getNavTitle() {
        return "Coaster Overview";
    }



    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
