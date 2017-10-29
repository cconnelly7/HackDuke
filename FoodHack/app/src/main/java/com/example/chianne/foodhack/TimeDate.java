package com.example.chianne.foodhack;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeDate extends AppCompatActivity {

    private View inflatedView;

    private Button requestSubmitButton;
    private TextView requestedDatetime;

    private Calendar myCalendar = Calendar.getInstance();
    private String timeStr = "";
    private String dateStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date);



        requestSubmitButton = (Button) findViewById(R.id.requestSubmitButton);
        requestedDatetime = (TextView) findViewById(R.id.requestedDatetime);

        requestSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = /*getActivity().*/getApplicationContext();
//                CharSequence text = "Delivery submitting...";
//                int duration = Toast.LENGTH_SHORT;

//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                //MapsActivity mapFragment = new MapsActivity();
                Intent myIntent = new Intent(getApplicationContext(), TrackingActivity.class);
                startActivity(myIntent);
                //getActivity().getSupportFragmentManager().findFragmentById(R.id.ordersFrame).setFragment(mapFragment);

                //FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
//                transaction.replace(R.id.ordersFrame, mapFragment);
//                transaction.addToBackStack(null);
//
//// Commit the transaction
//                transaction.commit();
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
                DatePickerDialog dp = new DatePickerDialog(getApplicationContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
                TimePickerDialog t = new TimePickerDialog(getApplicationContext(), time,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), false);
                t.show();
            };
        });


    }
}
