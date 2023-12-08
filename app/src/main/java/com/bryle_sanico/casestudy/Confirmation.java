package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
        Drawable defaultBorder = getDrawable(R.drawable.border);
        Drawable selectedBorder = getDrawable(R.drawable.selected_border);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Confirmation.this, MainActivity.class);
                    startActivity(intent);
                }
        });
        ccInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findViewById(R.id.ccInput).setBackground(selectedBorder);
                } else {
                    findViewById(R.id.ccInput).setBackground(defaultBorder);
                }
            }
        });
    }
}