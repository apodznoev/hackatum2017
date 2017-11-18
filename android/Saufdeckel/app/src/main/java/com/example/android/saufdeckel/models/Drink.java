package com.example.android.saufdeckel.models;

/**
 * Created by lars on 18.11.17.
 */

public class Drink {
    private final String mName;
    private final DrinkType mType;

    public Drink(String name, DrinkType type) {
        mName = name;
        mType = type;
    }

    public enum DrinkType {
        Beer, Cocktail, Shot
    }

    public String getName() {
        return mName;
    }
}
