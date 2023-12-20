package com.bryle_sanico.casestudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bryle_sanico.casestudy.ui.home.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bryle_sanico.casestudy.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Import statements

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressdialog;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private Intent directMain;
    private String URL = "https://rentonfind.site/";
    private String PHPFile = "";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        directMain = new Intent(MainActivity.this, MainActivity.class);

        requestQueue = Volley.newRequestQueue(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opening your messages..", Toast.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_messages)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Check if the user is logged in
        if (isLoggedIn()) {
            // User is logged in
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        } else {
            // User is not logged in
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
        }

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.nav_logout) {
                // Clear user data and perform logout
                logout();
                Intent logoutUser = new Intent(MainActivity.this, Login.class);
                startActivity(logoutUser);
                finish();
            }

            if (id == R.id.nav_login) {
                // Clear user data and perform logout
                Intent loginUser = new Intent(MainActivity.this, Login.class);
                startActivity(loginUser);
                finish();
            }

            // Close the navigation drawer
            drawer.closeDrawers();

            return true;
        });

        progressdialog = new ProgressDialog(this);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        if (!fetchAvailableUnits("MobileFetchUnit")) {
            Toast.makeText(MainActivity.this, "Failed! Please try again", Toast.LENGTH_LONG).show();
        }



    }

    public boolean fetchAvailableUnits(String PHPFile) {
        stringRequest = new StringRequest(Request.Method.GET, (URL + PHPFile), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    List<UnitModel> unitList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String unitID = jsonObject.getString("id");
                        String unitThumbnail = jsonObject.getString("thumbnail");
                        String locName = jsonObject.getString("title");
                        String locAddress = jsonObject.getString("landmark");
                        String paymentAmount = "₱"+jsonObject.getString("monthly_rent");
                        UnitModel unitModel = new UnitModel(unitID, unitThumbnail, locName, locAddress, paymentAmount);
                        unitList.add(unitModel);
                    }

                    homeViewModel.setUnitList(unitList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressdialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    // Function to perform logout
    private void logout() {
        // Clear the saved login status
        saveLoginStatus(false);

        // Navigate back to the login screen
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    // Function to check if the user is already logged in
    private boolean isLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    // Function to save login status using SharedPreferences
    private void saveLoginStatus(boolean isLoggedIn) {
        SharedPreferences preferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
}