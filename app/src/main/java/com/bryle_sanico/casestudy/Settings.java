package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Settings extends AppCompatActivity {
    ImageView borgir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        borgir = findViewById(R.id.burger);

    }
}