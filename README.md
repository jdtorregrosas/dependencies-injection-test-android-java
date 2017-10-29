# Dependencies Injection with Dagger2

## Steps

1. Add gradle dependencies
2. Create Modules to provide
3. Create component which will connect activities/services/fragments with modules
4. Create your Application class and use your component
5. Change the Application name on manifest
6. Create injected constructors if you need to customize the injector
7. Inject dependencies into your activities/fragments/services

## Explanation

### 1. Add gradle dependencies

You'll need dagger package and dagger annotationProcessor
```gradle
    compile 'com.google.dagger:dagger:2.11'
    annotationProcessor "com.google.dagger:dagger-compiler:2.11"
```

### 2. Create Modules

A module should have the @Module annotation and at least a method with @Provides

```java
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
```

### 3. Create Component

A component should have the @Component annotation and include the Modules needed.
Note that the method inject can be named as you prefer, but you should override it
depending on the classes which will use it to inject dependencies.

```java
@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface GlobalComponent {
    void inject(MainActivity activity);
    void inject(Main2Activity activity2);
}
```

### 4. Create Application class and use Component

```java
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
```

### 5. Change manifest

```xml
 <application
        android:name=".MyApplication"
        ...
        />
```

### 6. Create injected constructors

You can create custom classes in order to enhance the injection capabilities
So you'll inject the constructor of your custom class and now you can inject the class into your activities.

```java

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

```


### 7. Inject dependencies!!!

You need to inject the corresponding attribute and to execute the component coming from the application

```java
public class MainActivity extends AppCompatActivity {

    @Inject
    MySharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((MyApplication)getApplication()).getGlobalComponent().inject(this);

        ...

    }
}
```