package com.jdtorregrosas.dependenciesinjectiontestjava.components;

import com.jdtorregrosas.dependenciesinjectiontestjava.Main2Activity;
import com.jdtorregrosas.dependenciesinjectiontestjava.MainActivity;
import com.jdtorregrosas.dependenciesinjectiontestjava.modules.AppModule;
import com.jdtorregrosas.dependenciesinjectiontestjava.modules.SharedPreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by julian on 29/10/17.
 */

@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface GlobalComponent {
    void inject(MainActivity activity);
    void inject(Main2Activity activity2);
}

