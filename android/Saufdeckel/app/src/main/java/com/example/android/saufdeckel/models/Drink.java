package com.example.android.saufdeckel.models;

/**
 * Created by lars on 18.11.17.
 */

public class Drink {
    private final String mName;
    private final double mPrice;
    private final DrinkType mType;
    private double mStatus;

    public Drink(String name, double price, DrinkType type) {
        mName = name;
        mPrice = price;
        mType = type;
        mStatus = 1.0;
    }

    public String getName() {
        return mName;
    }

    public double getPrice() {
        return mPrice;
    }

    public double getStatus() {
        return mStatus;
    }

    public DrinkType getType() {
        return mType;
    }

    public enum DrinkType {
        BEER("Beer"), COCKTAIL("Fancy coctail"), SHOT("Shot");

        private final String uiName;

        DrinkType(String uiName) {
            this.uiName = uiName;
        }

        public String getUiName() {
            return uiName;
        }
    }

}
