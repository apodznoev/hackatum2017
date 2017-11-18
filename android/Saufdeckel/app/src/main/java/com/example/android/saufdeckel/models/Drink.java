package com.example.android.saufdeckel.models;

/**
 * Created by lars on 18.11.17.
 */

public class Drink {
    private final String mName;
    private final double mPrice;
    private final DrinkType mType;

    public Drink(String name, double mPrice, DrinkType type) {
        mName = name;
        this.mPrice = mPrice;
        mType = type;
    }

    public enum DrinkType {
        Beer, Cocktail, Shot
    }

    public String getmName() {
        return mName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public DrinkType getmType() {
        return mType;
    }

    public String getName() {
        return mName;
    }
}
