package com.bryle_sanico.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        List<String> itemList = Arrays.asList("100", "150", "200"); // Replace with your data

        CustomAdapterSaved adapter = new CustomAdapterSaved(this, itemList);

        ListView listView = findViewById(R.id.listViewMessages);
        listView.setAdapter(adapter);
    }
}