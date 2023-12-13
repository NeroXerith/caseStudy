package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Reports extends AppCompatActivity {

    EditText reportinput;
    Button continueBtn;
    ImageView borgir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        reportinput =  findViewById(R.id.reportInput);
        continueBtn = findViewById(R.id.continuebtn);
        borgir = findViewById(R.id.burger);
        Drawable defaultBorder = getDrawable(R.drawable.border);
        Drawable selectedBorder = getDrawable(R.drawable.selected_border);

        reportinput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    findViewById(R.id.ccInput).setBackground(selectedBorder);
                } else {
                    findViewById(R.id.ccInput).setBackground(defaultBorder);
                }
            }
        });
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueBtn.setBackgroundResource(R.drawable.button_pressed);
            }
        });
    }

}
