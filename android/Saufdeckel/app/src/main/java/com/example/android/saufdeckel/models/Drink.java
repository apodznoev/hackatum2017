package com.example.android.saufdeckel.models;

/**
 * Created by lars on 18.11.17.
 */

public class Drink {
    private final String mName;
    private final double mPrice;
    private double mStatus;
    private final DrinkType mType;

    public Drink(String name, double price, DrinkType type) {
        mName = name;
        mPrice = price;
        mType = type;
    }

    public enum DrinkType {
        BEER, COCKTAIL, SHOT
    }

    public String getName() {
        return mName;
    }

    public double getPrice() {
        return mPrice;
    }

    public double getStatus() {

    }

    public DrinkType getType() {
        return mType;
    }

}
