package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Confirmation extends AppCompatActivity {
    Button continueBtn;
    EditText ccInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        continueBtn = findViewById(R.id.continuebtn);
        ccInput = findViewById(R.id.ccInput);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Confirmation.this, MainActivity.class);
                    startActivity(intent);
                }
        });
    }
}