package com.bryle_sanico.casestudy;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class locationDetail extends AppCompatActivity {
    private ProgressDialog progressdialog;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String URL = "https://rentonfind.site/";
    private String PHPFile = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        requestQueue = Volley.newRequestQueue(this);
        progressdialog = new ProgressDialog(this);
        Intent intent = getIntent();
        if (intent != null) {
            String unitID = intent.getStringExtra("UNIT_ID");
            int intUnitID = Integer.parseInt(unitID);
            if (!fetchUnit("MobileFetchUnit", intUnitID)) {
                Toast.makeText(locationDetail.this, "Failed! Please try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean fetchUnit(String PHPFile, int unitID) {
        String fullURL = URL + PHPFile + "?id=" + unitID;
        stringRequest = new StringRequest(Request.Method.GET, fullURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    // Extracting the values from JSON
                    String locName = jsonObject.getString("title");
                    String unitTyppe = jsonObject.getString("type");
                    String locAddress = jsonObject.getString("landmark");
                    String paymentAmount = jsonObject.getString("monthly_rent");
                    String unitDescription = jsonObject.getString("description");

                    // Initialize Textviews
                    // Wala ka nito TextView locLandmark = findViewById();
                    TextView title = findViewById(R.id.profilename);
                    TextView description = findViewById(R.id.descText);

                    // Set the values of the Textview
                    title.setText(locName);
                    description.setText(unitDescription);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressdialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(locationDetail.this, error.getMessage(), Toast.LENGTH_LONG).show();
                progressdialog.hide();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("API_KEY", "TESTAPIKEY321");
                return params;
            }
        };

        // Add the request to the queue directly (no need for a separate thread)
        requestQueue.add(stringRequest);

        return true;
    }

}