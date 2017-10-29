package com.jdtorregrosas.dependenciesinjectiontestjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jdtorregrosas.dependenciesinjectiontestjava.helpers.MySharedPreferences;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity {

    @Inject
    MySharedPreferences sharedPreferences;

    TextView mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MyApplication)getApplication()).getGlobalComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mPrice = findViewById(R.id.main2_price);

        int price = sharedPreferences.getPrice();
        mPrice.setText(Integer.toString(price));

    }
}
