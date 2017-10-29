package com.example.chianne.foodhack;

import android.content.Intent;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    String currUserUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        chinese = (ImageView) findViewById(R.id.chinese);
        Picasso.with(this).load(R.drawable.chinese).resize(200, 200).into(chinese);
        chinese.setOnClickListener(this);
        italian = (ImageView) findViewById(R.id.italian);
        Picasso.with(this).load(R.drawable.italian).resize(200, 200).into(italian);
        italian.setOnClickListener(this);
        indian = (ImageView) findViewById(R.id.indian);
        Picasso.with(this).load(R.drawable.indian).resize(200, 200).into(indian);
        indian.setOnClickListener(this);
        mexican = (ImageView) findViewById(R.id.mexican);
        Picasso.with(this).load(R.drawable.mexican).resize(200, 200).into(mexican);
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

        saveBtn = (Button) findViewById(R.id.submitProfile);
        saveBtn.setOnClickListener(this);

        //loadProfile();

        if(currUser != null) {
            currUserUID = currUser.getUid();
        }


        foodList = new ArrayList<>();
        chineseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked() && !foodList.contains(((CheckBox) v).getText().toString())) {
                    foodList.add(((CheckBox) v).getText().toString());

                }
            }
        });

        italianB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked() && !foodList.contains(((CheckBox) v).getText().toString())) {
                    foodList.add(((CheckBox) v).getText().toString());

                }
            }
        });

        indianB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked() && !foodList.contains(((CheckBox) v).getText().toString())) {
                    foodList.add(((CheckBox) v).getText().toString());

                }
            }
        });

        mexicanB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked() && !foodList.contains(((CheckBox) v).getText().toString())) {
                    foodList.add(((CheckBox) v).getText().toString());

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == saveBtn) {
            submit();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

    public void submit() {
        DatabaseReference currUserRef = FirebaseDatabase.getInstance()
                .getReference("users").child(currUser.getUid());

        String name = firstName.getText().toString() + " " + lastName.getText().toString();
        currUserRef.child("name").setValue(name);

        String address = street.getText().toString() + "," + city.getText().toString() + ","
                + state.getText().toString() + "," + zip.getText().toString();
        currUserRef.child("address").setValue(address);

        if (!foodList.isEmpty()) {
            for (String f : foodList) {
                foods += (f + ",");
            }
            currUserRef.child("food").setValue(foods);
        }


        return;


    }


    public void loadProfile() {
        DatabaseReference currUserRef = FirebaseDatabase.getInstance()
                .getReference("users");//.child(currUser.getUid());


        currUserRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> userDataMap = (Map<String, Object>) dataSnapshot.getValue();

                String uid = (String) userDataMap.get("uid");
                String name = (String) userDataMap.get("name");
                String email = (String) userDataMap.get("email");
                //String dateTime = (String) userDataMap.get("datetime");
                String address = (String) userDataMap.get("address");
                String food = (String) userDataMap.get("food");

                if (uid.equals(currUserUID)) {
                    setFields(uid, name, email, address, food);
                }
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

    private void setFields(String uid, String name, String email,  String addr, String food) {
        String[] addressParsed = parseAddress(addr);
        String addr1 = addressParsed[0];
        String cityf = addressParsed[1];
        String statef = addressParsed[2];
        String zipf = addressParsed[3];

        String[] nameParsed = parseName(name);
        String fn = nameParsed[0];
        String ln = nameParsed[1];

        String[] foodParsed = parseFood(food);

        for (String f : foodParsed) {
            switch (f) {
                case "Chinese":
                    chineseB.setChecked(true);
                    break;
                case "Italian":
                    italianB.setChecked(true);
                    break;
                case "Mexican":
                    mexicanB.setChecked(true);
                    break;
                case "Indian":
                    indianB.setChecked(true);
                    break;

            }
        }

        firstName.setText(fn);
        lastName.setText(ln);
        street.setText(addr1);
        city.setText(cityf);
        state.setText(statef);
        zip.setText(zipf);

    }

    private String[] parseAddress(String addr) {
        return addr.split(",");

    }

    private String[] parseName(String name) {
        return name.split("\\s");
    }

    private String[] parseFood(String food) {
        return food.split(",");
    }


}
