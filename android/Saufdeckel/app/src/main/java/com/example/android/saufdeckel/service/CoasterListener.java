package com.example.android.saufdeckel.service;

import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;

import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public interface CoasterListener {

    void coastersLoaded(List<Coaster> coasters);
    /**
     * Triggered when level of last ordered drink for coaster is changed
     * @param coasterId id of coaster
     * @param drinkLevel remaining level in percentage
     */
    void drinkLevelChanged(int coasterId, double drinkLevel);

    /**
     * Triggered when coaster had finished his current drink
     * @param coasterId id of coaster
     */
    void drinkFinished(int coasterId);

    void newDrinkStarted(int coasterId, Drink newDrink);
}
