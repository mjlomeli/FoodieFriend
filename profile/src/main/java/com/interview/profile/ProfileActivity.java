package com.interview.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.interview.lib.Logo;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ArrayList<String> profile;
    ArrayList<Logo> logos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        startListView();
    }

    private void startListView(){
        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView recyclerViewFrame = (RecyclerView) findViewById(R.id.recyclerViewFrame);

        // Initialize contacts
        logos = Logo.logos();

        // Create adapter passing in the sample user data
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(logos);

        // Attach the adapter to the recyclerview to populate items
        recyclerViewFrame.setAdapter(adapter);

        // Set layout manager to position the items
        recyclerViewFrame.setLayoutManager(new LinearLayoutManager(this));
    }
}
