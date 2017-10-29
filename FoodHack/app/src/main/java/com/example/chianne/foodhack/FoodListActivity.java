package com.example.chianne.foodhack;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FoodListActivity extends Fragment implements View.OnClickListener {
    Context c;
    private Button cart;
    private Button checkout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        c = container.getContext();
        View v =  inflater.inflate(R.layout.activity_profile, container, false);
        cart = (Button) v.findViewById(R.id.cart_button);
        checkout = (Button) v.findViewById(R.id.checkout_button);
        cart.setOnClickListener(this);
        return inflater.inflate(R.layout.activity_food_list, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v == cart) {
            checkout.setVisibility(View.VISIBLE);
        }
    }

}
