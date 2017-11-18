package com.example.android.saufdeckel.service.impl;

import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public class CoastersStorage {
    private final List<Coaster> coasters = new ArrayList<>();

    public CoastersStorage() {
        coasters.add(new Coaster("Real Man", 13, "http://www.evilbeetgossip.com/wp-content/uploads/2012/09/Britney-Spears-5.jpg"));
        coasters.add(new Coaster("Charlie Sheen", 3, "http://i.dailymail.co.uk/i/pix/2012/03/12/article-0-121D6E7E000005DC-528_634x612.jpg"));
        coasters.add(new Coaster("Ozzy Osbourne", 5, "http://www.audio-music.info/pic/Osbourne_Ozzy.jpg"));
        coasters.add(new Coaster("Lindsey Lohan", 1, "http://popbytes.com/img/lindsay-lohan-jan5-JF.jpg"));
        coasters.add(new Coaster("Britney Spears", 4, "http://www.evilbeetgossip.com/wp-content/uploads/2012/09/Britney-Spears-5.jpg"));

        coasters.get(1).addDrink(new Drink("Gin Tonic", 7.35, Drink.DrinkType.COCKTAIL));
    }

    public void removeCoaster(Coaster coaster) {
        for (Iterator<Coaster> iterator = coasters.iterator(); iterator.hasNext(); ) {
            Coaster next = iterator.next();
            if(next == coaster) {
                iterator.remove();
                return;
            }
        }
    }

    public List<Coaster> getCoasters() {
        return coasters;
    }
}
