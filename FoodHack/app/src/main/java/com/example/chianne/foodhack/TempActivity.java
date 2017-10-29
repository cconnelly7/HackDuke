package com.example.chianne.foodhack;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TempActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv1;
    private ListView lv2;

    private List<String> foodItems;
    private List<String> cartItems;
    private ArrayAdapter<String> foodAdapter;
    private ArrayAdapter<String> cartAdapter;
    private EditText requestedDatetime;
    private Button submitBtn;

    private Calendar myCalendar = Calendar.getInstance();
    private String timeStr = "";
    private String dateStr = "";

    private long datetime;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currUser = mAuth.getCurrentUser();

    DatabaseReference foodListRef = FirebaseDatabase.getInstance().getReference("food-list");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        submitBtn = (Button) findViewById(R.id.submitOrder);
        submitBtn.setOnClickListener(this);

        requestedDatetime = (EditText) findViewById(R.id.requestedDatetime);

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


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                datetime = myCalendar.getTime().getTime();
                dateStr = format1.format(myCalendar.getTime());
                requestedDatetime.setText(dateStr + " " + timeStr);
            }

        };

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                timeStr = hourOfDay + ":" + minute;
                requestedDatetime.setText(dateStr + " " + timeStr);
            }
        };


        requestedDatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debugging", "pick a date");
                DatePickerDialog dp = new DatePickerDialog(TempActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
                TimePickerDialog t = new TimePickerDialog(TempActivity.this, time,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), false);
                t.show();
            };
        });

    }

    @Override
    public void onClick(View v) {
        if (v == submitBtn) {

            submitToDatabase();

            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void submitToDatabase() {
        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference("request-list");

        String key = requestRef.push().getKey();

        String items = "";

        for (String i : cartItems) {
            items += (i+",");
        }

        String uid = currUser.getUid();

        requestRef.child(key + "--" + uid).child("items").setValue(items);
        requestRef.child(key + "--" + uid).child("datetime").setValue(datetime);


    }


}
