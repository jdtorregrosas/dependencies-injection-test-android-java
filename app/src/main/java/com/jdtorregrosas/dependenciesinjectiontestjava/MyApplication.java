package com.jdtorregrosas.dependenciesinjectiontestjava;

import android.app.Application;

import com.jdtorregrosas.dependenciesinjectiontestjava.components.DaggerGlobalComponent;
import com.jdtorregrosas.dependenciesinjectiontestjava.components.GlobalComponent;
import com.jdtorregrosas.dependenciesinjectiontestjava.modules.AppModule;
import com.jdtorregrosas.dependenciesinjectiontestjava.modules.SharedPreferencesModule;

/**
 * Created by julian on 29/10/17.
 */

public class MyApplication extends Application {
    private GlobalComponent mGlobalComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        mGlobalComponent = DaggerGlobalComponent.builder()
                .appModule(new AppModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
    }

    public GlobalComponent getGlobalComponent(){
        return mGlobalComponent;
    }

}
