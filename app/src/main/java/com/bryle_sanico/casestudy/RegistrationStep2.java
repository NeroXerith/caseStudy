package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationStep2 extends AppCompatActivity {

    private static final String URL = "https://rentonfind.site/";

    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_step2);

        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);

        Intent directMain = new Intent(RegistrationStep2.this, MainActivity.class);

        Button continueBtn = findViewById(R.id.continuebtn);
        EditText fnameInput = findViewById(R.id.fnameInput);
        EditText mnameInput = findViewById(R.id.mnameInput);
        EditText lnameInput = findViewById(R.id.lnameInput);
        EditText inputAge = findViewById(R.id.inputAge);
        EditText inputContactNo = findViewById(R.id.inputContactNo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            password = extras.getString("password");
        }

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueBtn.setEnabled(false);

                String str_fname = fnameInput.getText().toString();
                String str_mname = mnameInput.getText().toString();
                String str_lname = lnameInput.getText().toString();
                String str_age = inputAge.getText().toString();
                String str_contact = inputContactNo.getText().toString();

                createAccount("MobileCreate", str_fname, str_mname, str_lname, email, str_contact, str_age, password);
            }
        });
    }

    private void createAccount(String phpFile, String fName, String mName, String lName, String user_email, String contact, String age, String user_pass) {
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL + phpFile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleServerResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleErrorResponse(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return buildParams(fName, mName, lName, user_email, contact, age, user_pass);
            }
        };

        requestQueue.add(stringRequest);
    }

    private void handleServerResponse(String response) {
        progressDialog.dismiss();

        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");

            if (message.equals("Success!")) {
                finish();
            } else {
                Toast.makeText(RegistrationStep2.this, message, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(RegistrationStep2.this, "Error parsing JSON response", Toast.LENGTH_LONG).show();
        }
    }

    private void handleErrorResponse(VolleyError error) {
        progressDialog.dismiss();
        Toast.makeText(RegistrationStep2.this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    private Map<String, String> buildParams(String fName, String mName, String lName, String user_email, String contact, String age, String user_pass) {
        Map<String, String> params = new HashMap<>();
        params.put("Fname", fName);
        params.put("Mname", mName);
        params.put("Lname", lName);
        params.put("Email", user_email);
        params.put("Contact", contact);
        params.put("password", user_pass);
        params.put("Age", age);
        return params;
    }
}
