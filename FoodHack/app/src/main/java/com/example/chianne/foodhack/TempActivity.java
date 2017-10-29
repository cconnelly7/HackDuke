package com.example.chianne.foodhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TempActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv1;
    private ListView lv2;

    private List<String> foodItems;
    private List<String> cartItems;
    private ArrayAdapter<String> foodAdapter;
    private ArrayAdapter<String> cartAdapter;

    private Button submitBtn;

    DatabaseReference foodListRef = FirebaseDatabase.getInstance().getReference("food-list");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        submitBtn = (Button) findViewById(R.id.submitOrder);
        submitBtn.setOnClickListener(this);

        foodItems = new ArrayList<>();
        cartItems = new ArrayList<>();

        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodItems);
        cartAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);


        lv1.setAdapter(foodAdapter);
        lv2.setAdapter(cartAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cartItems.add(foodItems.get(position));
                cartAdapter.notifyDataSetChanged();
            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cartItems.remove(position);
                cartAdapter.notifyDataSetChanged();
            }
        });

        foodListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = (String) ((Map) dataSnapshot.getValue()).get("name");
                Log.d("XXX", name);
                foodAdapter.add(name);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == submitBtn) {
            Intent intent = new Intent(getApplicationContext(), TimeDate.class);
            startActivity(intent);
        }
    }


}
