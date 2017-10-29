package com.example.chianne.foodhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodListActivity extends ListFragment {


    private DatabaseReference mDatabase;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void addItem(String foodName){
        listItems.add(foodName);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onStart() {

        super.onStart();
        getActivity().findViewById(R.id.back_button).requestFocus();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("imp", "setting up callback");
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.activity_list_item,
                listItems);
        setListAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference("food-info");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.exit(2);
                Log.d("imp","data change!!");
                for (DataSnapshot item_s:dataSnapshot.getChildren()) {
                    addItem((String)item_s.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("imp", databaseError.toString());
            }
        });
        return inflater.inflate(R.layout.activity_food_list, container, false);
    }

}
