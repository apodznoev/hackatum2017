package com.example.android.saufdeckel.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lars on 18.11.17.
 */

public class Coaster implements Parcelable {
    private final static AtomicInteger counter = new AtomicInteger(0);

    private final int mId;
    private final String mName;
    private int mTableNumber;
    private final List<Drink> mAllDrinks;
    private final String mImageUrl;

    public Coaster(String name, int tableNumber, String imageUrl) {
        mId = counter.incrementAndGet();
        mName = name;
        mTableNumber = tableNumber;
        mAllDrinks = new ArrayList<>();
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public int getTableNumber() {
        return mTableNumber;
    }

    public void addDrink(Drink drink) {
        mAllDrinks.add(drink);
    }

    public int getId() {
        return mId;
    }

    public Drink getCurrentDrink() {
        if (mAllDrinks.isEmpty())
            return null;
        return mAllDrinks.get(mAllDrinks.size() - 1);
    }

    public List<Drink> getAllDrinks() {
        return mAllDrinks;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeInt(mTableNumber);
        parcel.writeList(mAllDrinks);
        parcel.writeString(mImageUrl);
    }

    public static final Parcelable.Creator<Coaster> CREATOR
            = new Parcelable.Creator<Coaster>() {
        public Coaster createFromParcel(Parcel in) {
            return new Coaster(in);
        }

        public Coaster[] newArray(int size) {
            return new Coaster[size];
        }
    };

    private Coaster(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mTableNumber = in.readInt();
        mAllDrinks = new ArrayList<Drink>();
        in.readList(mAllDrinks, null);
        mImageUrl = in.readString();
    }
}
