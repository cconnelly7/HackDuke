package com.example.chianne.foodhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button profileBtn;
    private Button orderBtn;
    private Button mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        profileBtn = (Button) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(this);
        orderBtn = (Button) findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == profileBtn) {
            startActivity(new Intent(this, ProfileActivity.class));
        }

        if (v == orderBtn) {
            startActivity(new Intent(this, TempActivity.class));
        }

    }
}
