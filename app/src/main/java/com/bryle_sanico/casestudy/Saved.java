package com.bryle_sanico.casestudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class Saved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        List<String> itemList = Arrays.asList("100", "150", "200"); // Replace with your data

        CustomAdapterSaved adapter = new CustomAdapterSaved(this, itemList);

        ListView listView = findViewById(R.id.listViewSaved);
        listView.setAdapter(adapter);
    }
}
