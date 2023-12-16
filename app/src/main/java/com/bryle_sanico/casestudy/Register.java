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
    EditText inputEmail, inputPassword, pwConfirmation;
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
        pwConfirmation = findViewById(R.id.confirmpwInput);
        docs_placeholder = findViewById(R.id.docImBtn);


        if (RbOwner.isChecked()) {
            docs_placeholder.setVisibility(View.VISIBLE);
        }
        if (RbRenter.isChecked()){
            docs_placeholder.setVisibility(View.GONE);
        }
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueBtn.setBackgroundResource(R.drawable.button_pressed);

                // Check if passwords match
                if (isPasswordMatch()) {
                    Intent intent = new Intent(Register.this, RegistrationStep2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
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
        pwConfirmation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findViewById(R.id.confirmpwInput).setBackground(selectedBorder);
                } else {
                    findViewById(R.id.confirmpwInput).setBackground(defaultBorder);
                }
            }
        });
    }
    private boolean isPasswordMatch() {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = pwConfirmation.getText().toString().trim();
        return password.equals(confirmPassword);
    }
}