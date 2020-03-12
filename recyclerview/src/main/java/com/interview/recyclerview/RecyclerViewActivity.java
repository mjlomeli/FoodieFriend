package com.interview.recyclerview;

/**
 * @author Mauricio Lomeli
 * @version Feburary, 2020
 *
 * This activity is generates the list of the recommendation results.
 * It makes use of the RecyclerView to display results without pagination.
 * It displays the images of logos from an API and the results from the index.
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.interview.lib.Logo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RecyclerViewActivity extends AppCompatActivity {


    //////////  LAYOUT VARIABLES  //////////////////////////////////////////
    private RecyclerView recyclerView_Frame;

    //////////  Backend Variables   ////////////////////////////////////////
    ArrayList<Logo> logos;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //////  Layout Variables Assigned    //////////////////////////////
        recyclerView_Frame = (RecyclerView) findViewById(R.id.recyclerViewFrame);

        startListView();

        // connecting list to firebase NOTE: scroll down and click on real time database & select test mode
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete((ItemViewAdapter) adapter));
        //itemTouchHelper.attachToRecyclerView(recyclerView);


        // Firebase: must do these in order
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Items"); // gets the branch called Item from your DB
        // note if it doesn't exist, Firebase will create it.
        // Your database needs a unique key, else it will be overrided
        //String key = myRef.push().getKey(); // generates a random key for us
        //myRef.child(key).setValue("My db has this as a value");
        // on friday we will go over updating the recycler view


        /* //Demonstrations Only
        Intent intent = new Intent(this, MapsActivity.class);
        Pair<RecyclerViewActivity, Intent> pair = new Pair<>(this, intent);
        SleepTimer.delay(5, pair);
         */
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
