package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button Continuebtn,Registerbtn;

    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Continuebtn = findViewById(R.id.loginbtn);
        Registerbtn = findViewById(R.id.registerbtn);

        userInput = findViewById(R.id.emailInput);

        Continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.getText().equals("")) {
                    Toast.makeText(Login.this, "Empty Field", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.getText().equals("")) {
                    Toast.makeText(Login.this, "Empty Field", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                }
            }
        });
    }
}