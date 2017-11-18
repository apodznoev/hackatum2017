package com.example.android.saufdeckel.service.impl;

import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.service.CoasterListener;
import com.example.android.saufdeckel.service.CoastersService;

import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public class CoasterServiceImpl implements CoastersService {
    @Override
    public List<Coaster> getCoasters() {
        return null;
    }

    @Override
    public Coaster getCoasterById(int coasterId) {
        return null;
    }

    @Override
    public double checkoutCoaster(int coasterId) {
        return 0;
    }

    @Override
    public void setListener(CoasterListener listener) {

    }

    @Override
    public void start() {

    }
}
