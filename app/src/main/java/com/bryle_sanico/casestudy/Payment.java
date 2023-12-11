package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a FragmentTransaction
        fragmentManager.beginTransaction()
                .replace(R.id.paymentfragment, PaymentFragment1.newInstance("param1", "param2"))
                .commit();
    }
}
