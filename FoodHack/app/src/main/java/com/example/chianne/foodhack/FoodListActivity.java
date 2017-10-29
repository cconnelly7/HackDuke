package com.example.chianne.foodhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class FoodListActivity extends Fragment {


    private DatabaseReference mDatabase;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void addItem(String foodName){
        listItems.add(foodName);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        adapter = new ArrayAdapter<String>(getActivity(),
                R.id.item_listView,
                listItems);
        getActivity().findViewById(R.id.item_listView).setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mRef = mDatabase.getRef();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item_s:dataSnapshot.getChildren()) {
                    addItem((String)item_s.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.activity_food_list, container, false);
    }

}
