package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    ImageView docs_placeholder;
    EditText inputEmail, inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button continueBtn = findViewById(R.id.continuebtn);
        RadioButton RbOwner = findViewById(R.id.rbOwner);
        RadioButton RbRenter = findViewById(R.id.rbRenter);
        Drawable defaultBorder = getDrawable(R.drawable.border);
        Drawable selectedBorder = getDrawable(R.drawable.selected_border);
        inputEmail = findViewById(R.id.emailInput);
        inputPassword = findViewById(R.id.pwInput);

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
        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findViewById(R.id.emailInput).setBackground(selectedBorder);
                } else {
                    findViewById(R.id.emailInput).setBackground(defaultBorder);
                }
            }
        });
        inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findViewById(R.id.pwInput).setBackground(selectedBorder);
                } else {
                    findViewById(R.id.pwInput).setBackground(defaultBorder);
                }
            }
        });
    }
}