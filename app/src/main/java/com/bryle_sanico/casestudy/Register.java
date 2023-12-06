package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    ImageView docs_placeholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button continueBtn = findViewById(R.id.continuebtn);
        RadioButton RbOwner = findViewById(R.id.rbOwner);
        RadioButton RbRenter = findViewById(R.id.rbRenter);

        if (RbOwner.isChecked()) {
            docs_placeholder.setVisibility(View.VISIBLE);
        }
        if (RbRenter.isChecked()){
            docs_placeholder.setVisibility(View.GONE);
        }
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Confirmation.class);
                    startActivity(intent);
            }
        });
    }
}