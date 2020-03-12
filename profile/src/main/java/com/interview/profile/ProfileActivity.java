package com.interview.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.interview.lib.Logo;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    //////////  LAYOUT VARIABLES  //////////////////////////////////////////
    ImageView imageView_ProfileImage;

    TextView textView_UserName;
    TextView textView_TotalLikes;
    TextView textView_TotalDislikes;

    TabItem tabItem_ProfileTab;
    TabItem tabItem_GoalsTab;
    TabItem tabItem_DataTab;

        //// List Variables: DataTab
    ImageView imageView_LikeDislike;
    TextView textView_Category;

        //// List Variables: GoalsTab
    CheckBox checkBox_Goal;
    TextView textView_Goal;

        //// List Variables: ProfileTab
    TextView textView_AddressTitle;
    TextView textView_Address;

    RecyclerView recyclerView_Frame;


    //////////  Backend Variables   ////////////////////////////////////////
    ArrayList<String> profile;
    ArrayList<Logo> logos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //////  Layout Variables Assigned    //////////////////////////////
        imageView_ProfileImage = (ImageView) findViewById(R.id.imageView_ProfileImage);

        textView_UserName = (TextView) findViewById(R.id.textView_UserName);
        textView_TotalLikes = (TextView) findViewById(R.id.textView_TotalDislikes);
        textView_TotalDislikes = (TextView) findViewById(R.id.textView_TotalLikes);

        tabItem_ProfileTab = (TabItem) findViewById(R.id.tabItem_ProfileTab);
        tabItem_GoalsTab = (TabItem) findViewById(R.id.tabItem_GoalsTab);
        tabItem_DataTab = (TabItem) findViewById(R.id.tabItem_DataTab);

            //// List Variables: DataTab
        imageView_LikeDislike = (ImageView) findViewById(R.id.imageView_LikesDislikes);
        textView_Category = (TextView) findViewById(R.id.textView_Address);

            //// List Variables: GoalsTab
        checkBox_Goal = (CheckBox) findViewById(R.id.checkBox_Goals);
        textView_Goal = (TextView) findViewById(R.id.textView_Goals);

            //// List Variables: ProfileTab
        textView_AddressTitle = (TextView) findViewById(R.id.textView_AddressTitle);
        textView_Address =(TextView) findViewById(R.id.textView_Address);

        recyclerView_Frame = (RecyclerView) findViewById(R.id.recyclerViewFrame);

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
