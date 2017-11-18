package com.example.android.saufdeckel.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lars on 18.11.17.
 */

public class Coaster {
    private final static AtomicInteger counter = new AtomicInteger(0);
    private final int mId;
    private final String mName;
    private int mTableNumber;
    private final List<Drink> mAllDrinks;

    public Coaster(String name, int tableNumber) {
        mId = counter.incrementAndGet();
        mName = name;
        mTableNumber = tableNumber;
        mAllDrinks = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public int getTableNumber() {
        return mTableNumber;
    }

    public void addDrink(Drink drink) {
        mAllDrinks.add(drink);
    }

    public Drink getCurrentDrink() {
        if(mAllDrinks.isEmpty())
            return null;
        return mAllDrinks.get(mAllDrinks.size()-1);
    }

}
