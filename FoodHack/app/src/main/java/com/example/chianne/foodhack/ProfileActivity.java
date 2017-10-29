package com.example.chianne.foodhack;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName;
    private EditText lastName;
    private EditText street;
    private EditText city;
    private EditText state;
    private EditText zip;

    private ImageView chinese;
    private ImageView italian;
    private ImageView indian;
    private ImageView mexican;

    private CheckBox chineseB;
    private CheckBox italianB;
    private CheckBox indianB;
    private CheckBox mexicanB;

    private Button saveBtn;

    List<String> foodList;
    String foods = "";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currUser = mAuth.getCurrentUser();


@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        chinese = (ImageView) findViewById(R.id.chinese);
        Picasso.with(this).load(R.drawable.chinese).resize(200,200).into(chinese);
        chinese.setOnClickListener(this);
        italian = (ImageView) findViewById(R.id.italian);
        Picasso.with(this).load(R.drawable.italian).resize(200,200).into(italian);
        italian.setOnClickListener(this);
        indian = (ImageView) findViewById(R.id.indian);
        Picasso.with(this).load(R.drawable.indian).resize(200,200).into(indian);
        indian.setOnClickListener(this);
        mexican = (ImageView) findViewById(R.id.mexican);
        Picasso.with(this).load(R.drawable.mexican).resize(200,200).into(mexican);
        mexican.setOnClickListener(this);

        firstName = (EditText) findViewById(R.id.firstNameEdit);
        lastName = (EditText) findViewById(R.id.lastNameEdit);
        street = (EditText) findViewById(R.id.addressLineOneEdit);
        city = (EditText) findViewById(R.id.cityEdit);
        state = (EditText) findViewById(R.id.stateEdit);
        zip = (EditText) findViewById(R.id.zipCodeEdit);

        chineseB = (CheckBox) findViewById(R.id.chineseText);
        italianB = (CheckBox) findViewById(R.id.italianText);
        indianB = (CheckBox) findViewById(R.id.indianText);
        mexicanB = (CheckBox) findViewById(R.id.mexicanText);

        saveBtn = (Button) findViewById(R.id.submitProfile_);

        foodList = new ArrayList<>();
        if (chineseB.isChecked()) {
            foodList.add(chineseB.getText().toString());
        }
        if (italianB.isChecked()) {
            foodList.add(chineseB.getText().toString());
        }
        if (indianB.isChecked()) {
            foodList.add(chineseB.getText().toString());
        }
        if (mexicanB.isChecked()) {
            foodList.add(chineseB.getText().toString());
        }


        if (!foodList.isEmpty()) {
            for (String f : foodList) {
                foods += f;
            }
        }
        Log.d("xX", currUser.getUid());

    }

    @Override
    public void onClick(View v) {
        if (v == saveBtn) {
            submit();
        }
    }

    private void submit() {
        DatabaseReference currUserRef = FirebaseDatabase.getInstance()
                .getReference("users").child(currUser.getUid());
        Log.d("xX", currUser.getUid());

        String name = firstName.getText().toString() + " " + lastName.getText().toString();
        currUserRef.child("name").setValue(name);

        String address = street.getText().toString() + "," + city.getText().toString()
                + state.getText().toString() + "," + zip.getText().toString();
        currUserRef.child("address").setValue(address);

        currUserRef.child("food").setValue(foods);


    }


}
