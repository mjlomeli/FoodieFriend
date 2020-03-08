package com.interview.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.interview.androidlib.DownloadImage;
import com.interview.lib.Logo;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    /* need to do this everywhere we refer the database
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public void deleteItem(){
        StoreItem s (StoreItem)list.get(position);
        String key = s.getKey();
        myRef.child(key).removeValue();
        list.remove(position);
        notifyDataSetChanged();
    }
    *///end of database code


    // Provide a direct reference to each of the views within a data item_swipe
    // Used to cache the views within the item_swipe layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        protected ImageView imageView_logo;
        protected TextView textView_FoodName;
        protected TextView textView_FoodSize;
        protected TextView textView_FoodMods;
        protected TextView textView_DrinkName;
        protected TextView textView_DrinkSize;
        protected TextView textView_DrinkMods;

        // We also create a constructor that accepts the entire item_swipe row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageView_logo = (ImageView) itemView.findViewById(R.id.imageview_CompanyLogo);
            textView_FoodName = (TextView) itemView.findViewById(R.id.textView_FoodName);
            textView_FoodSize = (TextView) itemView.findViewById(R.id.textView_FoodSize);
            textView_FoodMods = (TextView) itemView.findViewById(R.id.textView_FoodMods);
            textView_DrinkName = (TextView) itemView.findViewById(R.id.textView_DrinkName);
            textView_DrinkSize = (TextView) itemView.findViewById(R.id.textView_DrinkSize);
            textView_DrinkMods = (TextView) itemView.findViewById(R.id.textView_FoodMods);
        }
    }

    // Store a member variable for the contacts
    private List<Logo> logos;

    // Pass in the contact array into the constructor
    public RecyclerViewAdapter(List<Logo> logos) {
        this.logos = logos;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_content, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item_swipe through holder
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Logo logos = this.logos.get(position);

        // Set item_swipe views based on your views and data model
        viewHolder.textView_FoodName.setText(logos.getName());

        new DownloadImage((ImageView) viewHolder.imageView_logo).execute("https://logo.clearbit.com/" + logos.getUrl());

        //Button button = viewHolder.messageButton;f
        //button.setText(contact.isOnline() ? "Visit" : "Closed");
        //button.setEnabled(contact.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return logos.size();
    }
}