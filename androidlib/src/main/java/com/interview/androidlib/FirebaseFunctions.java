package com.interview.androidlib;
/**
 * @author Mauricio Lomeli
 * @version Feburary, 2020
 *
 * This program holds various Firebase functions to help minimize the space
 *  taken up on the main activities.
 */

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseFunctions {
    public static void addMConditionRefListeners(DatabaseReference mConditionRef){
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
