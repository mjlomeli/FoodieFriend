package com.interview.swipeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipeActivity extends AppCompatActivity implements SwipeFlingAdapterView.onFlingListener, SwipeFlingAdapterView.OnItemClickListener {

    //private ArrayList<RowItem> imageContents;
    private ArrayAdapter<String> arrayAdapterImg;
    private int i;
    private SwipeFlingAdapterView flingContainer;

    private ArrayList<String> str = new ArrayList<>(Arrays.asList("chicken", "beef", "salad", "soup"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        /////////////////////////////////////////////////////////

        //imageContents = new ArrayList<>();
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "FastFood", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "Pizza", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "Sandwiches", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "Shipped","https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "Seafood","https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));
        //imageContents.add(new RowItem(R.drawable.ic_training_image_background, "Breakfast", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png"));

        //////////////////////////////////////////////////////

        arrayAdapterImg = new ArrayAdapter<>(this, R.layout.item_card, R.id.textView_card, str);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapterImg);
        flingContainer.setFlingListener(this);
        flingContainer.setOnItemClickListener(this);
    }

    @Override
    public void removeFirstObjectInAdapter() {
        // this is the simplest way to delete an object from the Adapter (/AdapterView)
        Log.d("LIST", "removed object!");
        str.remove(0);
        arrayAdapterImg.notifyDataSetChanged();
    }

    @Override
    public void onLeftCardExit(Object o) {
        //TODO: Log into Firebase the result
    }

    @Override
    public void onRightCardExit(Object o) {
        //TODO: Log into Firebase the result
    }

    @Override
    public void onAdapterAboutToEmpty(int i) {
        // Ask for more data here
        //str.add(new ImageContent("Food item: ".concat(String.valueOf(i)), "http://logo.clearbit.com/spotify.com?size=60"));
        str.add("ended");
        arrayAdapterImg.notifyDataSetChanged();
        Log.d("LIST", "notified");
        i++;
    }

    @Override
    public void onScroll(float v) {
        //View view = flingContainer.getSelectedView();
        //view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
        //view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
    }

    @Override
    public void onItemClicked(int i, Object o) {
        //TODO: Do you need to change the image when clicked/tapped?
    }


    public void onClick_Dislike(View view){
        flingContainer.getTopCardListener().selectLeft();
    }

    public void onClick_Like(View view){
        flingContainer.getTopCardListener().selectRight();
    }

    public void onClick_Info(View view){
        String message = "Swipe image left to like, swipe right to dislike";
        Toast.makeText(SwipeActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

