package com.jdtorregrosas.dependenciesinjectiontestjava.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by julian on 29/10/17.
 */

@Module
public class SharedPreferencesModule {
    private Context mContext;
    private static final String PREF_NAME = "GLOBAL_PREFS";

    public SharedPreferencesModule(Application application){
        this.mContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(){
        return mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
