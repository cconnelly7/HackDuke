package com.example.chianne.foodhack;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoadInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_in_screen);

        //ImageView imageView = (ImageView) findViewById(R.id.imageView5);

       // Picasso.with(this)
       //         .load("drawable/nourish")
       //         .into(imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(i);
            }
        }, 2000);

    }

}
