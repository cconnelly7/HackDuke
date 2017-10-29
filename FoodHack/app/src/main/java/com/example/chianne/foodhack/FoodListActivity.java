package com.example.chianne.foodhack;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodListActivity extends Fragment implements View.OnClickListener {
    Context c;
    private Button cart;
    private Button checkout;
    private DatabaseReference mDatabase;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private ListView lv1;
    private ListView lv2;

    private List<String> foodItems;
    private List<String> cartItems;
    private ArrayAdapter<String> foodAdapter;
    private ArrayAdapter<String> cartAdapter;

    DatabaseReference foodListRef = FirebaseDatabase.getInstance().getReference("food-list");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        c = container.getContext();
        View v = inflater.inflate(R.layout.activity_food_list, container, false);
        cart = (Button) v.findViewById(R.id.cart_button);
        checkout = (Button) v.findViewById(R.id.checkout_button);
        //cart.setOnClickListener(this);

        foodItems = new ArrayList<>();
        cartItems = new ArrayList<>();

        lv1 = (ListView) v.findViewById(R.id.item_listView);
        lv2 = (ListView) v.findViewById(R.id.item_listView2);
        foodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, foodItems);
        cartAdapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, cartItems);


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

//        foodListRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String name = (String) ((Map) dataSnapshot.getValue()).get("name");
//                Log.d("XXX", name);
//                foodItems.add(name);
//                foodAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        foodListRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot shot : dataSnapshot.getChildren()) {
//                    Log.d("xxx", shot.toString());
//                    String name = (String) ((Map) shot.getValue()).get("name");
//                    Log.d("XXX", name);
//                    foodItems.add(name);
//                    //foodAdapter.add(name);
//                    //foodAdapter.notifyDataSetChanged();
//                }
//                foodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodItems);
//                foodAdapter.notifyDataSetChanged();
//                lv1.setAdapter(foodAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });


        return inflater.inflate(R.layout.activity_food_list, container, false);
    }



}
