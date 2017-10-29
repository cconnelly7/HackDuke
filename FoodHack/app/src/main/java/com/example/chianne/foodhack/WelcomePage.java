package com.example.chianne.foodhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener {

    private Button loginHome;
    private Button registerHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //set up buttons
        loginHome = (Button) findViewById(R.id.loginHome);
        registerHome = (Button) findViewById(R.id.registerHome);

        loginHome.setOnClickListener(this);
        registerHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==loginHome) {
            Intent intent = new Intent(WelcomePage.this, LogOnActivity.class);
            WelcomePage.this.startActivity(intent);
            finish();
        }
    }
}
