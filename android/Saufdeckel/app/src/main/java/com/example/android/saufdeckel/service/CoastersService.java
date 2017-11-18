package com.example.android.saufdeckel.service;

import com.example.android.saufdeckel.models.Coaster;

import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public interface CoastersService {
    List<Coaster> getCoasters();

    Coaster getCoasterById(int coasterId);

    /**
     * @param coasterId
     * @return sum in euro to be payed
     */
    double checkoutCoaster(int coasterId);

    void setListener(CoasterListener listener);

    void start();
}
