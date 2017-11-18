package com.example.android.saufdeckel.models;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public void setWeight(double newWeight) {
        if (newWeight < 0) {
            return;
        }
        this.mStatus = newWeight;
    }

    public enum DrinkType {
        BEER("Beer", 700.0, 3.5, Arrays.asList("Augustiner", "Becks")),
        COCKTAIL("Fancy coctail", 400.0, 7.5, Arrays.asList("Bloody Mary", "White Russian")),
        SHOT("Shot", 80.0, 4.2, Arrays.asList("Jagermeister", "Vodka"));

        private final String uiName;
        private final double fullWeight;
        private final double price;
        private final List<String> namesPool;

        DrinkType(String uiName, double fullWeight, double price, List<String> namesPool) {
            this.uiName = uiName;
            this.fullWeight = fullWeight;
            this.price = price;
            this.namesPool = namesPool;
        }

        public double getFullWeight() {
            return fullWeight;
        }

        public String getUiName() {
            return uiName;
        }

        public double getPrice() {
            return price;
        }

        public String getRandomName() {
            return namesPool.get((int) (System.currentTimeMillis() % namesPool.size()));
        }
    }

}
