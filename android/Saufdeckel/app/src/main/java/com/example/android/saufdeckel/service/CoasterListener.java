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
     */
    void drinkLevelChanged(Coaster updatedCoaster);

    /**
     * Triggered when coaster had finished his current drink
     */
    void drinkFinished(Coaster updatedCoaster);

    void newDrinkStarted(Coaster updatedCoaster);
}
