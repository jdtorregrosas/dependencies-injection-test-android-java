package com.jdtorregrosas.dependenciesinjectiontestjava.helpers;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by julian on 29/10/17.
 */

public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;
    private static final String PRICE_KEY = "PRICE";

    @Inject
    public MySharedPreferences(SharedPreferences sharedPreferences){
        this.mSharedPreferences = sharedPreferences;
    }

    public void putPrice(int price){
        mSharedPreferences.edit().putInt(PRICE_KEY, price).apply();
    }

    public int getPrice(){
        return mSharedPreferences.getInt(PRICE_KEY, 0 );
    }
}
