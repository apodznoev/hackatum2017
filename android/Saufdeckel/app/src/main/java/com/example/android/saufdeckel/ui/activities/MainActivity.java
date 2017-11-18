package com.example.android.saufdeckel.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.ui.fragments.BaseParentFragment;
import com.example.android.saufdeckel.ui.fragments.overview.CoasterOverviewFragment;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private BaseParentFragment mCurrentFragment;
    public static Stack<String> _mFragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // set the Above View
        if (savedInstanceState != null) {
            mCurrentFragment = (BaseParentFragment)fragmentManager.getFragment(savedInstanceState, "currentFragment");
        }
        if (_mFragmentStack == null) {
            _mFragmentStack = new Stack<String>();
            mCurrentFragment = CoasterOverviewFragment.newInstance();
        }

        if (mCurrentFragment != null) {
            addFragment(mCurrentFragment);

        }
    }

    @Override
    public void onBackPressed(){
        if (_mFragmentStack.size() > 1 ){
            // Remove the fragment
            removeFragment();
            mCurrentFragment.setNavTitle();
        }
    }

    private void addFragment(BaseParentFragment fragment) {
        String tag = fragment.toString();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, fragment, tag);
        transaction.addToBackStack(tag);
        _mFragmentStack.add(tag);
        transaction.commit();

        mCurrentFragment = fragment;
    }

    public void changeFragment(BaseParentFragment fragment) {

        String tag = fragment.toString();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentByTag(_mFragmentStack.peek());
        transaction.hide(currentFragment);

        transaction.add(R.id.fl_content, fragment, tag);
        transaction.addToBackStack(tag);
        _mFragmentStack.add(tag);

        transaction.commit();

        mCurrentFragment = fragment;
    }

    private void removeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentByTag(_mFragmentStack.pop());
        Fragment fragment = fragmentManager.findFragmentByTag(_mFragmentStack.peek());
        transaction.remove(currentFragment);
        transaction.show(fragment);
        transaction.commit();

        mCurrentFragment = (BaseParentFragment)fragment;
    }

}
