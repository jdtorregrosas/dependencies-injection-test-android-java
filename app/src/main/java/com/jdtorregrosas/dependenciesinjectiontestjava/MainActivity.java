package com.jdtorregrosas.dependenciesinjectiontestjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jdtorregrosas.dependenciesinjectiontestjava.helpers.MySharedPreferences;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MySharedPreferences sharedPreferences;

    EditText mPrice, mQty;
    Button mShowPrice;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((MyApplication)getApplication()).getGlobalComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowPrice = findViewById(R.id.main_cta_show_price);
        mPrice = findViewById(R.id.main_price);
        mQty = findViewById(R.id.main_quantity);

        mShowPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price  = Integer.parseInt(mPrice.getText().toString());
                int qty = Integer.parseInt(mQty.getText().toString());
                sharedPreferences.putPrice(price * qty);
                startActivity(new Intent(mContext, Main2Activity.class));
            }
        });



    }
}
