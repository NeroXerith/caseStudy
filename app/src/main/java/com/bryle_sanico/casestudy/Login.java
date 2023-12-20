package com.bryle_sanico.casestudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button Loginbtn, Registerbtn;
    private EditText inputEmail, inputPassword;
    private Intent directMain;
    private String URL = "https://rentonfind.site/";
    private RequestQueue requestQueue;
    private ProgressDialog progressdialog;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressdialog = new ProgressDialog(this); // Initialize progress dialog

        Loginbtn = findViewById(R.id.loginbtn);
        Registerbtn = findViewById(R.id.registerbtn);

        inputEmail = findViewById(R.id.emailInput);
        inputPassword = findViewById(R.id.pwInput);

        // Direct to the main
        directMain = new Intent(Login.this, MainActivity.class);

        requestQueue = Volley.newRequestQueue(this); // Initialize request queue
        // Check if the user is already logged in
        if (isLoggedIn()) {
            saveLoginStatus(true);
            startActivity(directMain);
            finish();
        } else {
            // Hide the "Login" menu item in MainActivity
            hideLoginMenuItem();
            Loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressdialog.setMessage("Sending Information...");
                    progressdialog.show();
                    String str_username = inputEmail.getText().toString();
                    String str_password = inputPassword.getText().toString();
                    // Check if login is successful
                    LoginAccount("MobileLogin", str_username, str_password);
                }
            });
        }

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    // Log an error or show a Toast indicating the issue
                    Toast.makeText(Login.this, "Error starting Register activity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Function to save login status using SharedPreferences
    private void saveLoginStatus(boolean isLoggedIn) {
        SharedPreferences preferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    // Function to check if the user is already logged in
    private boolean isLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    public void LoginAccount(String PHPFile, String UserEmail, String PassWord) {
        stringRequest = new StringRequest(Request.Method.POST, (URL + PHPFile), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success!")) {  // user is logged in
                    // Save login status
                    saveLoginStatus(true);

                    // Open MainActivity
                    startActivity(directMain);
                    finish();
                } else {
                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                }
                progressdialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                progressdialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_email", UserEmail);
                params.put("user_password", PassWord);
                return params;
            }
        };

        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        requestQueue.add(stringRequest);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
    }
    private void hideLoginMenuItem() {
        NavigationView navigationView = findViewById(R.id.mobile_navigation);
        if (navigationView != null) {
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        }
    }
}
