package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private ProgressDialog progressdialog;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private Button Loginbtn, Registerbtn;
    private EditText inputEmail, inputPassword;
    private Intent directMain;
    private String URL = "https://rentonfind.000webhostapp.com/functions/", PHPFile = "";

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

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.setMessage("Sending Information...");
                progressdialog.show();
                String str_username = inputEmail.getText().toString();
                String str_password = inputPassword.getText().toString();
                // SUPPLY THE USERNAME AND PASSWORD DATA FROM THE TEXT FIELD
                if (!LoginAccount("login.php", str_username, str_password)) {
                    Toast.makeText(Login.this, "Login Failed! Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Empty Field", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean LoginAccount(String PHPFile, String UserEmail, String PassWord) {
        stringRequest = new StringRequest(Request.Method.POST, (URL + PHPFile), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success!")) {  // user is logged in
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
        return true;
    }
}
