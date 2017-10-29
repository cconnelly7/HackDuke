package com.example.chianne.foodhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogOnActivity extends AppCompatActivity implements View.OnClickListener{

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        back = (Button) findViewById(R.id.login_back_btn);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            //Goes back to the main splash screen
            Intent intent = new Intent(LogOnActivity.this, WelcomePage.class);
            LogOnActivity.this.startActivity(intent);
            finish();
        }
    }
}
