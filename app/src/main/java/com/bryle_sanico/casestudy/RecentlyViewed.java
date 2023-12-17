package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class RecentlyViewed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_viewed);
        List<String> itemList = Arrays.asList("100", "150", "200"); // Replace with your data

        CustomAdapterRecentlyViewed adapter = new CustomAdapterRecentlyViewed(this, itemList);

        ListView listView = findViewById(R.id.listViewRv);
        listView.setAdapter(adapter);
    }
}