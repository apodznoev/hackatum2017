package com.example.android.saufdeckel.service.impl;

import com.example.android.saufdeckel.models.Coaster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public class CoastersStorage {
    private final List<Coaster> coasters = new ArrayList<>();

    public void removeCoaster(Coaster coaster) {

    }

    public List<Coaster> getCoasters() {
        return coasters;
    }
}
