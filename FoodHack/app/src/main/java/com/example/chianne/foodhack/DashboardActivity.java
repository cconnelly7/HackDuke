package com.example.chianne.foodhack;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button profileBtn;
    private Button orderBtn;
    //private ImageView triangles;
    //private Button mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //triangles = (ImageView) findViewById(R.id.background);
        //Picasso.with(this).load(R.drawable.triangles).resize(900,1200).into(triangles);


        profileBtn = (Button) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(this);
        orderBtn = (Button) findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(this);
        //mapBtn = (Button) findViewById(R.id.mapBtn);
        //mapBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == profileBtn) {
            startActivity(new Intent(this, ProfileActivity.class));
        }

        if (v == orderBtn) {
            startActivity(new Intent(this, TempActivity.class));
        }

        /*if (v == mapBtn) {
            startActivity(new Intent(this, OrdersActivity.class));
        }*/

    }
}
